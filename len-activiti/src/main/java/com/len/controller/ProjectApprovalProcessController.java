package com.len.controller;

import com.alibaba.fastjson.JSON;
import com.len.base.BaseController;
import com.len.core.annotation.Log;
import com.len.entity.*;
import com.len.service.*;
import com.len.util.CommonUtil;
import com.len.util.JsonUtil;
import com.len.util.ReType;
import com.len.util.RoleUtil;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.*;
import org.activiti.engine.task.Task;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("")
public class ProjectApprovalProcessController extends BaseController {

    @Autowired
    SysUserService sysUserService;

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

    @Autowired
    ProjectWorkerInfoService projectWorkerInfoService;

    @Autowired
    ProWorInfoManService proWorInfoManService;

    @GetMapping({"showProjDetail", "share/showProjDetail"})
    public String showProjDetail(Model model, String projId) {
        ProjectInfo projectInfo = projectInfoService.selectByPrimaryKey(projId);
        System.out.println(JSON.toJSONString(projectInfo));
        model.addAttribute("projectDetail", projectInfo);
        return "act/project/projDetail";
    }

    @ApiOperation(value = "项目上级：项目通过、驳回", httpMethod = "POST")
    @Log(desc = "主管项目")
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
        if (flag) {
            projectInfo.setProjState("已立项"); // 状态：已立项
            ProjectWorkerInfo workerInfo = new ProjectWorkerInfo();
            workerInfo.setProId(projId);
            workerInfo.setProName(projectInfo.getProjName());
            workerInfo.setProStatus("notFinish");
            workerInfo.setPmId(projectInfo.getPmId());
            workerInfo.setPmName(projectInfo.getPmName());
            projectWorkerInfoService.insert(workerInfo);
        } else projectInfo.setProjState("已驳回"); //状态：已驳回
        projectInfoService.updateByPrimaryKey(projectInfo);
        return JsonUtil.sucess("操作成功！");
    }

    @PostMapping("/project/projReady")
    @RequiresRoles(RoleUtil.PM_ROLE_ID)
    @ResponseBody
    public JsonUtil projReady(String projId) {
        ProjectInfo projectInfo = projectInfoService.selectByPrimaryKey(projId);
        projectInfo.setProjState("已交付");
        projectInfoService.updateByPrimaryKey(projectInfo);
        return JsonUtil.sucess("操作成功！");
    }

    @PostMapping("/project/projEnd")
    @RequiresRoles(RoleUtil.PM_ROLE_ID)
    @ResponseBody
    public JsonUtil projEnd(String projId) {
        ProjectInfo projectInfo = projectInfoService.selectByPrimaryKey(projId);
        projectInfo.setProjState("结束");
        projectInfoService.updateByPrimaryKey(projectInfo);
        return JsonUtil.sucess("操作成功！");
    }

    @PostMapping("/project/archiveCheck")
    @RequiresRoles(RoleUtil.PM_ROLE_ID)
    @ResponseBody
    public JsonUtil archiveCheck(String projId) {
        ProjectInfo projectInfo = projectInfoService.selectByPrimaryKey(projId);
        projectInfo.setProjState("申请归档");
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


    @GetMapping("/epgSetting")
    @RequiresRoles(RoleUtil.EPG_LEADER_ROLE_ID)
    public String epgSetting(Model model, String projId) {
        model.addAttribute("projId", projId);
        return "act/project/setEpg";
    }

    @PostMapping("/setUpEPG")
    @RequiresRoles(RoleUtil.EPG_LEADER_ROLE_ID)
    @ResponseBody
    public JsonUtil setProjEPG(String projId, String epgManager, String epgName) {
        ProjectInfo projectInfo = projectInfoService.selectByPrimaryKey(projId);
        projectInfo.setEpgManager(epgManager);
        projectInfo.setEpgName(epgName);
        projectInfoService.updateByPrimaryKey(projectInfo);
        SysUser user = sysUserService.selectByPrimaryKey(epgManager);
        ProWorInfoMan man = new ProWorInfoMan();
        man.setId(UUID.randomUUID().toString());
        man.setUserId(epgManager);
        man.setUserName(epgName);
        man.setUserEmail(user.getEmail());
        man.setUserPhone(user.getPhone());
        man.setProRoleName("epg");
        man.setProId(projId);
        man.setProName(projectInfo.getProjName());
        man.setPmId(projectInfo.getPmId());
        proWorInfoManService.insert(man);
        String instanceId = projectInfo.getProcessInstanceId();
        List<Task> tasks = this.taskService.createTaskQuery()
                .processInstanceId(instanceId)
                .taskCandidateGroup(RoleUtil.EPG_LEADER_ROLE_ID)
                .list();
        taskService.complete(tasks.get(0).getId());
        return JsonUtil.sucess("操作成功！");
    }

    @GetMapping("/qaSetting")
    @RequiresRoles(RoleUtil.QA_LEADER_ROLE_ID)
    public String qaSetting(Model model, String projId) {
        model.addAttribute("projId", projId);
        return "act/project/setQA";
    }

    @PostMapping("/setUpQA")
    @RequiresRoles(RoleUtil.QA_LEADER_ROLE_ID)
    @ResponseBody
    public JsonUtil setProjQA(String projId, String qaManager, String qaName) {
        ProjectInfo projectInfo = projectInfoService.selectByPrimaryKey(projId);
        projectInfo.setQaManager(qaManager);
        projectInfoService.updateByPrimaryKey(projectInfo);
        SysUser user = sysUserService.selectByPrimaryKey(qaManager);
        ProWorInfoMan man = new ProWorInfoMan();
        man.setId(UUID.randomUUID().toString());
        man.setUserId(qaManager);
        man.setUserName(qaName);
        man.setUserEmail(user.getEmail());
        man.setUserPhone(user.getPhone());
        man.setProRoleName("qa");
        man.setProId(projId);
        man.setProName(projectInfo.getProjName());
        man.setPmId(projectInfo.getPmId());
        proWorInfoManService.insert(man);
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
        Map<String, String> map = new HashMap<>();
        map.put("conf", RoleUtil.CONF_MASTER_ROLE_ID);
        map.put("qa", RoleUtil.QA_LEADER_ROLE_ID);
        map.put("epg", RoleUtil.EPG_LEADER_ROLE_ID);
        model.addAllAttributes(map);
        return "act/project/mytasks";
    }

    @GetMapping("/qa")
    @RequiresRoles(RoleUtil.QA_LEADER_ROLE_ID)
    public String setProjQA(Model model) {
        Map<String, String> map = new HashMap<>();
        map.put("conf", RoleUtil.CONF_MASTER_ROLE_ID);
        map.put("qa", RoleUtil.QA_LEADER_ROLE_ID);
        map.put("epg", RoleUtil.EPG_LEADER_ROLE_ID);
        model.addAllAttributes(map);
        return "act/project/mytasks";
    }

    @GetMapping("/conf")
    @RequiresRoles(RoleUtil.CONF_MASTER_ROLE_ID)
    public String checkProjConf(Model model) {
        Map<String, String> map = new HashMap<>();
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
        if (set.contains(RoleUtil.CHIEF_ROLE_ID) || set.contains(RoleUtil.CONF_MASTER_ROLE_ID)) {
            myActiTasks = taskService.createTaskQuery()
                    .taskAssignee(myId)
                    .list();
        }
        if (set.contains(RoleUtil.EPG_LEADER_ROLE_ID)) {
            myActiTasks.addAll(
                    taskService.createTaskQuery()
                            .taskCandidateGroup(RoleUtil.EPG_LEADER_ROLE_ID)
                            .list()
            );
        }
        if (set.contains(RoleUtil.QA_LEADER_ROLE_ID)) {
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

    @GetMapping("/share/proj")
    public String allProj() {
        return "act/project/share";
    }

    @GetMapping("/share/getAllProj")
    @ResponseBody
    public ReType showPMprojctList(String proName, int page, int limit) {
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setProjName(proName);
        return projectInfoService.show(projectInfo, page, limit);
    }
}