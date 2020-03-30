package com.len.controller;

import com.len.base.BaseController;
import com.len.service.ProjectInfoService;
import com.len.service.RoleUserService;
import org.activiti.engine.*;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
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
    ProcessEngineFactoryBean processEngine;

    @Autowired
    ProcessEngineConfiguration processEngineConfiguration;

    @Autowired
    RoleUserService roleUserService;


//https://www.bilibili.com/video/BV1k4411B7mu?p=53 驳回 批准
//    https://www.cnblogs.com/haore147/p/5213467.html
}