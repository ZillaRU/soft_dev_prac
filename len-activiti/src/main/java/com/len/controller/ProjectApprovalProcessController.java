package com.len.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.len.base.BaseController;
import com.len.entity.ProjectInfo;
import com.len.service.ProjectInfoService;
import com.len.service.RoleUserService;
import com.len.util.Base64Utils;
import com.len.util.JsonUtil;
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
    @ResponseBody
    public JsonUtil chiefCheck(String projId, boolean flag) {
        ProjectInfo projectInfo = projectInfoService.selectByPrimaryKey(projId);
        String instanceId = projectInfo.getProcessInstanceId();
        Task task = this.taskService.createTaskQuery().processInstanceId(instanceId).singleResult();
        Map<String, Object> map = new HashMap<>();
        map.put("flag", flag);
        taskService.complete(task.getId(), map);
        if(flag) {
            projectInfo.setProjState(1); // 状态：已立项
        } else projectInfo.setProjState(-1); //状态：已驳回
        projectInfoService.updateByPrimaryKey(projectInfo);
        return JsonUtil.sucess("操作成功！");
    }

    @GetMapping("/epg/setEpg")
    public String setProjEpg() {
        return "act/project/setEpg";
    }

    @GetMapping("/qa/setQA")
    public String setProjQA() {
        return "act/project/setQA";
    }

    @GetMapping("/conf/checkProjConf")
    public String checkProjConf() {
        return "act/project/checkProjConf";
    }
}