package com.len.controller;

import com.alibaba.fastjson.JSON;
import com.len.core.shiro.Principal;
import com.len.entity.ActModel;
import com.len.entity.ProjectWorkerInfo;
import com.len.entity.SysUser;
import com.len.service.ProjectWorkerInfoService;
import com.len.service.SysUserService;
import com.len.util.JsonUtil;
import org.activiti.engine.RepositoryService;
import com.len.util.ReType;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.repository.ModelQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.len.core.annotation.Log;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/projectWorker")

public class ProjectWorkerInfoController {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ProjectWorkerInfoService projectWorkerInfoService;

    @Autowired
    private SysUserService userService;

    @GetMapping("showInfo")
    public String showInfo(Model model) {
        String id = Principal.getPrincipal().getId();
        SysUser user = userService.selectByPrimaryKey(id);
        System.out.println(JSON.toJSONString(user));
        model.addAttribute("user", user);
        return "act/projectWorker/showWorkerInfo";
    }

    @ApiOperation(value = "项目人员信息", httpMethod = "GET")
    @Log(desc = "当前项目经理管理的项目人员信息")
    @GetMapping("info")
    @ResponseBody
    public ReType proWorkerInfo(Model mol, String proName, ProjectWorkerInfo worInfo, String page, String limit) {
        String id = Principal.getPrincipal().getId();
        worInfo.setPmId(id);
        worInfo.setProName(proName);
        List<ProjectWorkerInfo> worInf = projectWorkerInfoService.selectByProName(worInfo);
        //List<ProjectWorkerInfo> list = projectWorkerInfoService.selectByPmId(Principal.getPrincipal().getId());
        return new ReType(worInf.size(), worInf);
    }


}


