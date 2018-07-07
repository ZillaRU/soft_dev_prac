/**
 * Copyright 2018 lenos
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.len.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.len.base.BaseController;
import com.len.base.CurrentUser;
import com.len.core.shiro.ShiroUtil;
import com.len.entity.LeaveOpinion;
import com.len.entity.UserLeave;
import com.len.exception.MyException;
import com.len.service.UserLeaveService;
import com.len.util.BeanUtil;
import com.len.util.CommonUtil;
import com.len.util.JsonUtil;
import com.len.util.ReType;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricVariableUpdate;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhuxiaomeng
 * @date 2018/1/21.
 * @email 154040976@qq.com
 * <p>
 * 请假流程示例
 */
@Controller
@RequestMapping("/leave")
public class UserLeaveController extends BaseController {

    @Autowired
    UserLeaveService leaveService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    IdentityService identityService;

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    ProcessEngineFactoryBean processEngine;

    @Autowired
    ProcessEngineConfiguration processEngineConfiguration;

    private String leaveOpinionList = "leaveOpinionList";


    @GetMapping(value = "showLeave")
    public String showUser(Model model) {
        return "/act/leave/leaveList";
    }

    @GetMapping(value = "showLeaveList")
    @ResponseBody
    public ReType showLeaveList(Model model, UserLeave userLeave, String page, String limit) {
        String userId = CommonUtil.getUser().getId();
        userLeave.setUserId(userId);
        List<UserLeave> tList = null;
        Page<UserLeave> tPage = PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
        try {
            tList = leaveService.selectListByPage(userLeave);
            for (UserLeave leave : tList) {
                ProcessInstance instance = runtimeService.createProcessInstanceQuery()
                        .processInstanceId(leave.getProcessInstanceId()).singleResult();
                //保证运行ing
                if (instance != null) {
                    Task task = this.taskService.createTaskQuery().processInstanceId(leave.getProcessInstanceId()).singleResult();
                    leave.setTaskName(task.getName());
                }
            }
        } catch (MyException e) {
            e.printStackTrace();
        }
        return new ReType(tPage.getTotal(), tList);
    }

    /**
     * 根据 执行对象id获取审批信息
     *
     * @param model
     * @param processId
     * @return
     */
    @GetMapping("leaveDetail")
    public String leaveDetail(Model model, String processId) {
        ProcessInstance instance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processId).singleResult();
        //保证运行ing
        List<LeaveOpinion> leaveList = null;
        List<HistoricActivityInstance> historicActivityInstanceList = new ArrayList<>();
        if (instance != null) {
            Task task = this.taskService.createTaskQuery().processInstanceId(processId).singleResult();
            Map<String, Object> variables = taskService.getVariables(task.getId());
            Object o = variables.get(leaveOpinionList);
            if (o != null) {
        /*获取历史审核信息*/
                leaveList = (List<LeaveOpinion>) o;
            }
        } else {
            leaveList = new ArrayList<>();
            List<HistoricDetail> list = historyService.createHistoricDetailQuery().
                    processInstanceId(processId).list();
            HistoricVariableUpdate variable = null;
            for (HistoricDetail historicDetail : list) {
                variable = (HistoricVariableUpdate) historicDetail;
                String variableName = variable.getVariableName();
                if (leaveOpinionList.equals(variable.getVariableName())) {
                    leaveList.clear();
                    leaveList.addAll((List<LeaveOpinion>) variable.getValue());
                }
            }
        }
        model.addAttribute("leaveDetail", JSON.toJSONString(leaveList));
        return "/act/leave/leaveDetail";
    }

    @GetMapping("addLeave")
    public String addLeave() {
        return "/act/leave/add-leave";
    }

    @GetMapping("updateLeave/{taskId}")
    public String updateLeave(Model model, @PathVariable String taskId) {
        Map<String, Object> variables = taskService.getVariables(taskId);
        UserLeave leave = (UserLeave) variables.get("userLeave");
        leave = leaveService.selectByPrimaryKey(leave.getId());
        model.addAttribute("leave", leave);
        model.addAttribute("taskId", taskId);
        return "/act/leave/update-leave";
    }

    @PostMapping("updateLeave/updateLeave/{taskId}/{id}/{flag}")
    @ResponseBody
    public JsonUtil updateLeave(UserLeave leave, @PathVariable String taskId, @PathVariable String id, @PathVariable boolean flag) {
        JsonUtil j = new JsonUtil();
        try {
            UserLeave oldLeave = leaveService.selectByPrimaryKey(leave.getId());
            BeanUtil.copyNotNullBean(leave, oldLeave);
            leaveService.updateByPrimaryKeySelective(oldLeave);

            Map<String, Object> variables = taskService.getVariables(taskId);
            UserLeave userLeave = (UserLeave) variables.get("userLeave");
            Map<String, Object> map = new HashMap<>();
            if (flag) {
                map.put("flag", true);
            } else {
                map.put("flag", false);
            }
            taskService.complete(taskId, map);
            j.setMsg("保存成功");
        } catch (MyException e) {
            j.setMsg("保存失败");
            j.setFlag(false);
            e.printStackTrace();
        }
        return j;
    }


    @PostMapping("addLeave")
    @ResponseBody
    public JsonUtil addLeave(Model model, UserLeave userLeave) {
        JsonUtil j = new JsonUtil();
        if (userLeave == null) {
            return JsonUtil.error("获取数据失败");
        }
        userLeave.setDays(3);
        CurrentUser user = CommonUtil.getUser();
        userLeave.setUserId(user.getId());
        userLeave.setUserName(user.getUsername());
        userLeave.setProcessInstanceId("2018");//模拟数据

        leaveService.insertSelective(userLeave);
        Map<String, Object> map = new HashMap<>();
        map.put("userLeave", userLeave);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("process_leave", map);
        userLeave.setProcessInstanceId(processInstance.getId());
        UserLeave userLeave1 = leaveService.selectByPrimaryKey(userLeave.getId());
        BeanUtil.copyNotNullBean(userLeave, userLeave1);
        leaveService.updateByPrimaryKeySelective(userLeave1);
        if (processInstance == null) {
            return JsonUtil.error("未识别key");
        }
        j.setMsg("请假申请成功");
        return j;
    }

    /**
     * ---------我的任务---------
     */
    @GetMapping(value = "showTask")
    public String showTask(Model model) {
        return "/act/task/taskList";
    }

    @GetMapping(value = "showTaskList")
    @ResponseBody
    public String showTaskList(Model model, com.len.entity.Task task, String page, String limit) {
        CurrentUser user = CommonUtil.getUser();
        List<Task> taskList = taskService.createTaskQuery().taskCandidateUser(user.getId()).list();
        List<com.len.entity.Task> tasks = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        com.len.entity.Task taskEntity = null;

        Map<String, Map<String, Object>> mapMap = new HashMap<>();
        Map<String, Object> objectMap = null;
        for (Task task1 : taskList) {
            objectMap = new HashMap<>();
            map = taskService.getVariables(task1.getId());
            UserLeave userLeave = (UserLeave) map.get("userLeave");

            taskEntity = new com.len.entity.Task(task1);
            taskEntity.setUserName(userLeave.getUserName());
            taskEntity.setReason(userLeave.getReason());
            /**如果是自己*/
            if (user.getId().equals(userLeave.getUserId()) && !(boolean) map.get("flag")) {
                objectMap.put("flag", true);
            } else {
                objectMap.put("flag", false);
            }
            mapMap.put(taskEntity.getId(), objectMap);
            tasks.add(taskEntity);
        }
        return ReType.jsonStrng(taskList.size(), tasks, mapMap, "id");
    }

    @GetMapping("agent/{id}")
    public String agent(Model model, @PathVariable("id") String taskId) {
        Map<String, Object> variables = taskService.getVariables(taskId);
        UserLeave userLeave = (UserLeave) variables.get("userLeave");
        userLeave = leaveService.selectByPrimaryKey(userLeave.getId());
        model.addAttribute("leave", userLeave);
        model.addAttribute("taskId", taskId);
        return "/act/task/task-agent";
    }

    @PostMapping("agent/complete")
    @ResponseBody
    public JsonUtil complete(LeaveOpinion op, HttpServletRequest request) {
        Map<String, Object> variables = taskService.getVariables(op.getTaskId());
        UserLeave userLeave = (UserLeave) variables.get("userLeave");
        CurrentUser user = ShiroUtil.getCurrentUse();
        op.setCreateTime(new Date());
        op.setOpId(user.getId());
        op.setOpName(user.getRealName());
        JsonUtil j = new JsonUtil();
        Map<String, Object> map = new HashMap<>();
        map.put("flag", op.isFlag());
        //审批信息叠加
        List<LeaveOpinion> leaveList = new ArrayList<>();
        Object o = variables.get(leaveOpinionList);
        if (o != null) {
            leaveList = (List<LeaveOpinion>) o;
        }
        leaveList.add(op);
        map.put(leaveOpinionList, leaveList);
        j.setMsg("审核成功" + (op.isFlag() ? "<font style='color:green'>[通过]</font>" : "<font style='color:red'>[未通过]</font>"));
        taskService.complete(op.getTaskId(), map);
        return j;
    }

    @Autowired
    HistoryService historyService;

    /**
     * 追踪图片成图
     *
     * @param request
     * @param resp
     * @param processInstanceId
     * @throws IOException
     */
    @GetMapping("getProcImage")
    public void getProcImage(HttpServletRequest request, HttpServletResponse resp, String processInstanceId)
            throws IOException {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        HistoricProcessInstance historicProcessInstance =
                historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        String processDefinitionId = null;
        List<String> executedActivityIdList = new ArrayList<String>();
        if (processInstance != null) {
            processDefinitionId = processInstance.getProcessDefinitionId();
            executedActivityIdList = this.runtimeService.getActiveActivityIds(processInstance.getId());
        } else if (historicProcessInstance != null) {
            processDefinitionId = historicProcessInstance.getProcessDefinitionId();
            List<HistoricActivityInstance> historicActivityInstanceList =
                    historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).orderByHistoricActivityInstanceId().asc().list();
            for (HistoricActivityInstance activityInstance : historicActivityInstanceList) {
                executedActivityIdList.add(activityInstance.getActivityId());
            }
        }

        if (StringUtils.isEmpty(processDefinitionId) || executedActivityIdList.isEmpty()) {
            return;
        }
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        //List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);
        processEngineConfiguration = processEngine.getProcessEngineConfiguration();
        Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);
        ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
        //List<String> activeIds = this.runtimeService.getActiveActivityIds(processInstance.getId());

        InputStream imageStream = diagramGenerator.generateDiagram(
                bpmnModel, "png",
                executedActivityIdList, Collections.<String>emptyList(),
                processEngine.getProcessEngineConfiguration().getActivityFontName(),
                processEngine.getProcessEngineConfiguration().getLabelFontName(),
                "宋体",
                null, 1.0);
        byte[] b = new byte[1024];
        int len;
        while ((len = imageStream.read(b, 0, 1024)) != -1) {
            resp.getOutputStream().write(b, 0, len);
        }
    }

}
