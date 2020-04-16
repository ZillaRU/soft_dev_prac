package com.len.controller;

import com.alibaba.fastjson.JSON;
import com.len.core.annotation.Log;
import com.len.core.shiro.Principal;
import com.len.entity.ProjectInfo;
import com.len.service.ProjectInfoService;
import com.len.service.RoleUserService;
import com.len.service.SysUserService;
import com.len.util.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class ProjectArchiveController {
    @Autowired
    private ProjectInfoService projectInfoService;

    @Autowired
    private SysUserService userService;

    @Autowired
    RoleUserService roleUserService;

    @GetMapping("showArchive")
    public String showArchive() {
        return "act/projectArchive/PoArchive";
    }

    @ApiOperation(value = "某用户要归档的项目", httpMethod = "GET")
    @Log(desc = "项目归档管理")
    @GetMapping("showArchiveList")
    @ResponseBody
    public ReType showArchiveList() {
        List<ProjectInfo> list = projectInfoService.selectByEPGIdandState(Principal.getPrincipal().getId());
        return new ReType(list.size(), list);
    }

    @GetMapping("showProArchive")
    public String showProArchive(Model model, String projId) {
        ProjectInfo projectInfo = projectInfoService.selectByPrimaryKey(projId);
        System.out.println(JSON.toJSONString(projectInfo));
        model.addAttribute("projectID", projId);
        return "act/projectArchive/showArchive";
    }

    @PostMapping("proCheck")
    @ResponseBody
    public JsonUtil proCheck(String projId) {
        System.out.println(1);
        ProjectInfo projectInfo = projectInfoService.selectByPrimaryKey(projId);
        projectInfo.setProjState("已归档");
        projectInfoService.updateByPrimaryKey(projectInfo);
        return JsonUtil.sucess("操作成功");
    }
}