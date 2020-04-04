package com.len.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.len.base.BaseController;
import com.len.entity.BaseTask;
import com.len.entity.ProjectInfo;
import com.len.service.ProjectInfoService;
import com.len.service.RoleUserService;
import com.len.util.*;
import io.swagger.annotations.ApiOperation;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.HMProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Controller
@RequestMapping("")
public class ProjectApprovalProcessController extends BaseController {

    @Autowired
    ProjectInfoService projectInfoService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    IdentityService identityService;

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    HistoryService historyService;

    @Autowired
    ProcessEngineFactoryBean processEngine;

    @Autowired
    ProcessEngineConfiguration processEngineConfiguration;

    @Autowired
    RoleUserService roleUserService;

    // https://www.bilibili.com/video/BV1k4411B7mu?p=53 驳回 批准
    // https://www.cnblogs.com/haore147/p/5213467.html
    @PostMapping("/project/chiefCheck")
    @RequiresRoles(RoleUtil.CHIEF_ROLE_ID)
    @ResponseBody
    public JsonUtil chiefCheck(String projId, boolean flag) {
        ProjectInfo projectInfo = projectInfoService.selectByPrimaryKey(projId);
        String instanceId = projectInfo.getProcessInstanceId();
        Task task = this.taskService.createTaskQuery().processInstanceId(instanceId).singleResult();
        Map<String, Object> map = new HashMap<>();
        map.put("flag", flag);
        taskService.complete(task.getId(), map);
        if(flag) {
            projectInfo.setProjState("已立项"); // 状态：已立项
        } else projectInfo.setProjState("已驳回"); //状态：已驳回
        projectInfoService.updateByPrimaryKey(projectInfo);
        return JsonUtil.sucess("操作成功！");
    }

    @PostMapping("/confCheck")
    @RequiresRoles(RoleUtil.CONF_MASTER_ROLE_ID)
    @ResponseBody
    public JsonUtil confCheck(String projId) {
        ProjectInfo projectInfo = projectInfoService.selectByPrimaryKey(projId);
        String instanceId = projectInfo.getProcessInstanceId();
        List<Task> tasks = this.taskService.createTaskQuery()
                .processInstanceId(instanceId)
                .taskAssignee(CommonUtil.getUser().getId())
                .list();
        taskService.complete(tasks.get(0).getId());
        return JsonUtil.sucess("操作成功！");
    }

    @PostMapping("/project/setUpEPG")
    @RequiresRoles(RoleUtil.EPG_LEADER_ROLE_ID)
    @ResponseBody
    public JsonUtil setProjEPG(String projId, String uid) {
        ProjectInfo projectInfo = projectInfoService.selectByPrimaryKey(projId);
        String instanceId = projectInfo.getProcessInstanceId();
        List<Task> tasks = this.taskService.createTaskQuery()
                .processInstanceId(instanceId)
                .taskCandidateGroup(RoleUtil.EPG_LEADER_ROLE_ID)
                .list();

        taskService.complete(tasks.get(0).getId());
        return JsonUtil.sucess("操作成功！");
    }

    @PostMapping("/project/setUpQA")
    @RequiresRoles(RoleUtil.QA_LEADER_ROLE_ID)
    @ResponseBody
    public JsonUtil setProjQA(String projId, String uid) {
        ProjectInfo projectInfo = projectInfoService.selectByPrimaryKey(projId);
        String instanceId = projectInfo.getProcessInstanceId();
        List<Task> tasks = this.taskService.createTaskQuery()
                .processInstanceId(instanceId)
                .taskCandidateGroup(RoleUtil.QA_LEADER_ROLE_ID)
                .list();

        taskService.complete(tasks.get(0).getId());
        return JsonUtil.sucess("操作成功！");
    }

    @GetMapping("/epg")
    @RequiresRoles(RoleUtil.EPG_LEADER_ROLE_ID)
    public String setProjEpg(Model model) {
        Map<String,String> map = new HashMap<>();
        map.put("conf", RoleUtil.CONF_MASTER_ROLE_ID);
        map.put("qa", RoleUtil.QA_LEADER_ROLE_ID);
        map.put("epg", RoleUtil.EPG_LEADER_ROLE_ID);
        model.addAllAttributes(map);
        return "act/project/mytasks";
    }

    @GetMapping("/qa")
    @RequiresRoles(RoleUtil.QA_LEADER_ROLE_ID)
    public String setProjQA(Model model) {
        Map<String,String> map = new HashMap<>();
        map.put("conf", RoleUtil.CONF_MASTER_ROLE_ID);
        map.put("qa", RoleUtil.QA_LEADER_ROLE_ID);
        map.put("epg", RoleUtil.EPG_LEADER_ROLE_ID);
        model.addAllAttributes(map);
        return "act/project/mytasks";
    }

    @GetMapping("/conf")
    @RequiresRoles(RoleUtil.CONF_MASTER_ROLE_ID)
    public String checkProjConf(Model model) {
        Map<String,String> map = new HashMap<>();
        map.put("conf", RoleUtil.CONF_MASTER_ROLE_ID);
        map.put("qa", RoleUtil.QA_LEADER_ROLE_ID);
        map.put("epg", RoleUtil.EPG_LEADER_ROLE_ID);
        model.addAllAttributes(map);
        return "act/project/mytasks";
    }

    @ApiOperation(value = "需要某用户处理的项目", httpMethod = "GET")
    @GetMapping("myTasks")
    @ResponseBody
    public String needCheck() {
        String myId = CommonUtil.getUser().getId();
        Set<String> set = new HashSet<>(roleUserService.selectByUserId(myId));
        List<Task> myActiTasks = new ArrayList<>();
        List<com.len.entity.Task> tasks = new ArrayList<>();
        Map<String, Map<String, Object>> mapMap = new HashMap<>();
        if(set.contains(RoleUtil.CHIEF_ROLE_ID) || set.contains(RoleUtil.CONF_MASTER_ROLE_ID)){
            myActiTasks = taskService.createTaskQuery()
                    .taskAssignee(myId)
                    .list();
        }
        if(set.contains(RoleUtil.EPG_LEADER_ROLE_ID)) {
            myActiTasks.addAll(
                    taskService.createTaskQuery()
                    .taskCandidateGroup(RoleUtil.EPG_LEADER_ROLE_ID)
                    .list()
            );
        }
        if(set.contains(RoleUtil.QA_LEADER_ROLE_ID)) {
            myActiTasks.addAll(
                    taskService.createTaskQuery()
                            .taskCandidateGroup(RoleUtil.QA_LEADER_ROLE_ID)
                            .list()
            );
        }
        Map<String, Object> map, objectMap;
        com.len.entity.Task taskEntity;
        Set<String> taskSet = new HashSet<String>();
        for (Task task1 : myActiTasks) {
            objectMap = new HashMap<>();
            String taskId = task1.getId();
            if (taskSet.contains(taskId)) {
                continue;
            }
            map = taskService.getVariables(taskId);
            BaseTask projApply = (BaseTask) map.get("baseTask");

            taskEntity = new com.len.entity.Task(task1);
            taskEntity.setPmName(projApply.getUserName());
            taskEntity.setProjName(projApply.getProjName());
            taskEntity.setUrlpath(projApply.getId());
            mapMap.put(taskEntity.getId(), objectMap);
            tasks.add(taskEntity);
            taskSet.add(taskId);
        }
        return ReType.jsonStrng(tasks.size(), tasks, mapMap, "id");
    }
}