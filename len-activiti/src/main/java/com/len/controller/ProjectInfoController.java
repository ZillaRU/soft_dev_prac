package com.len.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.len.core.annotation.Log;
import com.len.core.shiro.Principal;
import com.len.entity.*;
import com.len.exception.MyException;
import com.len.service.*;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


@Controller
@RequestMapping(value = "/project")
public class ProjectInfoController {
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private SysUserService userService;

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

    @Autowired
    ProjFuncService projFuncService;

    @Autowired
    ProjectWorkerInfoService projectWorkerInfoService;

    @GetMapping(value = "showApply", produces = "application/json;charset=utf-8")
    public String applyProject(Model model) {
        String id = Principal.getPrincipal().getId();
        SysUser user = userService.selectByPrimaryKey(id);
        model.addAttribute("user", user);
        return "act/project/apply-proj";
    }

    @ApiOperation(value = "申报项目", httpMethod = "POST")
    @Log(desc = "申报项目")
    @PostMapping("applyProject")
    @ResponseBody
    public JsonUtil applyProject(ProjectInfo projectInfo) {
        JsonUtil j = new JsonUtil();
        String msg = "项目信息添加成功";
        // projectInfo.setProjState("申请立项");
        projectInfo.setUserId(projectInfo.getPmId());
        projectInfo.setUserName(projectInfo.getPmName());
        projectInfo.setPmId(Principal.getPrincipal().getId());
        System.out.println(projectInfo.toString());
        try {
            projectInfoService.insertSelective(projectInfo);
            Map<String, Object> map = new HashMap<>();
            projectInfo.setUrlpath("/showProjDetail/projId=" + projectInfo.getId());
            map.put("baseTask", projectInfo);
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("ProjectApply", map);
            System.out.println("\nprocessInstanceId" + processInstance + "\n");
            projectInfo.setProcessInstanceId(processInstance.getId());
            ProjectInfo proj_with_proc = projectInfoService.selectByPrimaryKey(projectInfo.getId());
            BeanUtil.copyNotNullBean(projectInfo, proj_with_proc);
            Task task = this.taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
            System.out.println("taskName " + task.getName());
            SysUser currUser = userService.selectByPrimaryKey(CommonUtil.getUser().getId());
            // 可在监听器中实现
            // 完成：提交项目申请
            Map<String, Object> vars = new HashMap<>();
            // 获取上级的邮箱
            String chiefId = currUser.getChiefId();
            SysUser user = userService.selectByPrimaryKey(chiefId);
            String chiefMailAddr = "";
            if (user != null) {
                chiefMailAddr = user.getEmail();
            } else {
                System.out.println("这是脏数据吗？查无此人！");
            }
            vars.put("chief_mail", chiefMailAddr);
            vars.put("pm_name", currUser.getRealName());
            vars.put("pm_mail", currUser.getEmail());
            vars.put("qa_mail", userService.getUserByRoleId(RoleUtil.QA_LEADER_ROLE_ID).get(0).getEmail());
            vars.put("epg_mail", userService.getUserByRoleId(RoleUtil.EPG_LEADER_ROLE_ID).get(0).getEmail());
            vars.put("conf_mail", userService.getUserByRoleId(RoleUtil.CONF_MASTER_ROLE_ID).get(0).getEmail());
            vars.put("proj_name", projectInfo.getProjName());
            System.out.println("task----> " + task);
            taskService.complete(task.getId(), vars);
            // taskService.complete(task.getId());
            task = this.taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
            proj_with_proc.setTaskName(task.getName());
            // 设定下个节点的处理人
            taskService.setAssignee(task.getId(), chiefId);
            System.out.println("task after----> " + task);
            projectInfoService.updateByPrimaryKeySelective(proj_with_proc);
        } catch (MyException e) {
            msg = "保存失败";
            j.setFlag(false);
            e.printStackTrace();
        }
        j.setMsg(msg);
        return j;
    }

    @GetMapping("showPMproject")
    public String showPMproject() {
        return "act/project/my-pm-project";
    }

    @ApiOperation(value = "某用户主管的项目", httpMethod = "GET")
    @Log(desc = "主管项目")
    @GetMapping("showPMprojctList")
    @ResponseBody
    public ReType showPMprojctList(String proName, int page, int limit) {
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setPmId(Principal.getPrincipal().getId());
        projectInfo.setProjName(proName);
        return projectInfoService.show(projectInfo, page, limit);
    }

    @GetMapping("showProjDetail")
    public String showProjDetail(Model model, String projId) {
        ProjectInfo projectInfo = projectInfoService.selectByPrimaryKey(projId);
        System.out.println(JSON.toJSONString(projectInfo));
        model.addAttribute("projectDetail", projectInfo);
        return "act/project/projDetail";
    }

    @ApiOperation(value = "审批项目申请页", httpMethod = "GET")
    @GetMapping("showApprovals")
    public String showApprovals() {
        return "act/project/mytasks";
    }

    @ApiOperation(value = "需要某用户处理的项目", httpMethod = "GET")
    @GetMapping("myTasks")
    @ResponseBody
    public String needCheck() {
        String myId = CommonUtil.getUser().getId();
        // refer: https://www.cnblogs.com/jiqiyoudu/p/4704866.html
        List<Task> myActiTasks = taskService.createTaskQuery()
                .taskAssignee(myId)
                .list();
        List<com.len.entity.Task> tasks = new ArrayList<>();
        Map<String, Map<String, Object>> mapMap = new HashMap<>();
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

    @GetMapping("projFunc")
    public String setProjFuncs(Model model, String projId) {
        model.addAttribute("projectId", projId);
        return "act/project/proj-func";
    }

    @GetMapping("showProjFunc")
    @ResponseBody
    public ReType showProjFuncs(String projId, String funcName, int page, int limit) {
        // List<ProjectFunction> list = projFuncService.selectByProjId(projId);
        ReType reType = projFuncService.show(new ProjectFunction(projId, funcName), page, limit);
        List<?> list = reType.getData();
        ProjectWorkerInfo info = new ProjectWorkerInfo();
        info.setProId(projId);
        if (page == 0 && !list.isEmpty() && projectWorkerInfoService.selectByPrimaryKey(info) != null) {
            ProjectInfo projectInfo = new ProjectInfo();
            projectInfo.setId(projId);
            projectInfo = projectInfoService.selectByPrimaryKey(projectInfo);
            if (projectInfo.getEpgManager() != null && projectInfo.getQaManager() != null) {
                Task task = this.taskService.createTaskQuery().processInstanceId(projectInfoService.selectByPrimaryKey(projId).getProcessInstanceId()).singleResult();
                if (task != null && !task.getAssignee().equals(RoleUtil.CONF_MASTER_ROLE_ID)) {
                    taskService.complete(task.getId());
                    projectInfo.setProjState("进行中");
                    projectInfoService.updateByPrimaryKeySelective(projectInfo);
                }
            }
        }
        return reType;
    }

    @GetMapping("updateFunc")
    public String updateFunc(Model model, String projId, String funcId) {
        model.addAttribute("projectId", projId);
        model.addAttribute("funcId", funcId);
        return "act/project/editFunc";
    }

    @PostMapping("editFunc")
    @ResponseBody
    public JsonUtil updateRiskInfo(ProjectFunction func) {
        JsonUtil j = new JsonUtil();
        String msg = "更新项目功能成功";
        try {
            projFuncService.updateByCoPrimaryKey(func);
        } catch (MyException e) {
            msg = "保存失败";
            j.setFlag(false);
            e.printStackTrace();
        }
        j.setMsg(msg);
        return j;
    }

    @GetMapping("/projApprovalProcess")
    public String showProjProcess(Model model, String processInstanceId) {
        model.addAttribute("processInstanceId", processInstanceId);
        return "/act/project/shinePics";
    }

    @GetMapping("/getShineProcImage")
    @ResponseBody
    public String getShineProcImage(HttpServletRequest request, HttpServletResponse resp, String processInstanceId)
            throws IOException {
        JSONObject result = new JSONObject();
        JSONArray shineProImages = new JSONArray();
        InputStream imageStream = generateStream(request, resp, processInstanceId, true);
        if (imageStream != null) {
            String imageCurrentNode = Base64Utils.ioToBase64(imageStream);
            if (StringUtils.isNotBlank(imageCurrentNode)) {
                shineProImages.add(imageCurrentNode);
            }
        }
        InputStream imageNoCurrentStream = generateStream(request, resp, processInstanceId, false);
        if (imageNoCurrentStream != null) {
            String imageNoCurrentNode = Base64Utils.ioToBase64(imageNoCurrentStream);
            if (StringUtils.isNotBlank(imageNoCurrentNode)) {
                shineProImages.add(imageNoCurrentNode);
            }
        }
        result.put("id", UUID.randomUUID().toString());
        result.put("errorNo", 0);
        result.put("images", shineProImages);
        return result.toJSONString();
    }

    public InputStream generateStream(HttpServletRequest request, HttpServletResponse resp, String processInstanceId, boolean needCurrent) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        HistoricProcessInstance historicProcessInstance =
                historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        System.out.println(processInstance + "----\n" + processInstanceId + "----\n" + historicProcessInstance + "---\n");
        String processDefinitionId = null;
        List<String> executedActivityIdList = new ArrayList<String>();
        List<String> currentActivityIdList = new ArrayList<>();
        List<HistoricActivityInstance> historicActivityInstanceList = new ArrayList<>();
        if (processInstance != null) {
            processDefinitionId = processInstance.getProcessDefinitionId();
            if (needCurrent) {
                currentActivityIdList = this.runtimeService.getActiveActivityIds(processInstance.getId());
            }
        }
        if (historicProcessInstance != null) {
            processDefinitionId = historicProcessInstance.getProcessDefinitionId();
            historicActivityInstanceList =
                    historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).orderByHistoricActivityInstanceId().asc().list();
            for (HistoricActivityInstance activityInstance : historicActivityInstanceList) {
                executedActivityIdList.add(activityInstance.getActivityId());
            }
        }

        if (StringUtils.isEmpty(processDefinitionId) || executedActivityIdList.isEmpty()) {
            return null;
        }

        //高亮线路id集合
        ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
        List<String> highLightedFlows = getHighLightedFlows(definitionEntity, historicActivityInstanceList);

        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        //List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);
        processEngineConfiguration = processEngine.getProcessEngineConfiguration();
        Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);
        HMProcessDiagramGenerator diagramGenerator = (HMProcessDiagramGenerator) processEngineConfiguration.getProcessDiagramGenerator();
        //List<String> activeIds = this.runtimeService.getActiveActivityIds(processInstance.getId());

        InputStream imageStream = diagramGenerator.generateDiagram(
                bpmnModel, "png",
                executedActivityIdList, highLightedFlows,
                processEngine.getProcessEngineConfiguration().getActivityFontName(),
                processEngine.getProcessEngineConfiguration().getLabelFontName(),
                "宋体",
                null, 1.0, currentActivityIdList);

        return imageStream;
    }

    /**
     * 获取需要高亮的线
     *
     * @param processDefinitionEntity
     * @param historicActivityInstances
     * @return
     */
    private List<String> getHighLightedFlows(
            ProcessDefinitionEntity processDefinitionEntity,
            List<HistoricActivityInstance> historicActivityInstances) {

        List<String> highFlows = new ArrayList<String>();// 用以保存高亮的线flowId
        for (int i = 0; i < historicActivityInstances.size() - 1; i++) {// 对历史流程节点进行遍历
            ActivityImpl activityImpl = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i)
                            .getActivityId());// 得到节点定义的详细信息
            List<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();// 用以保存后需开始时间相同的节点
            ActivityImpl sameActivityImpl1 = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i + 1)
                            .getActivityId());
            // 将后面第一个节点放在时间相同节点的集合里
            sameStartTimeNodes.add(sameActivityImpl1);
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
                HistoricActivityInstance activityImpl1 = historicActivityInstances
                        .get(j);// 后续第一个节点
                HistoricActivityInstance activityImpl2 = historicActivityInstances
                        .get(j + 1);// 后续第二个节点
                if (activityImpl1.getStartTime().equals(
                        activityImpl2.getStartTime())) {
                    // 如果第一个节点和第二个节点开始时间相同保存
                    ActivityImpl sameActivityImpl2 = processDefinitionEntity
                            .findActivity(activityImpl2.getActivityId());
                    sameStartTimeNodes.add(sameActivityImpl2);
                } else {
                    // 有不相同跳出循环
                    break;
                }
            }
            List<PvmTransition> pvmTransitions = activityImpl
                    .getOutgoingTransitions();// 取出节点的所有出去的线
            for (PvmTransition pvmTransition : pvmTransitions) {
                // 对所有的线进行遍历
                ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition
                        .getDestination();
                // 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {
                    highFlows.add(pvmTransition.getId());
                }
            }
        }
        return highFlows;
    }
}
