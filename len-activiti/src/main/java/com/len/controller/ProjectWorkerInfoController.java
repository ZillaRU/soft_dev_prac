package com.len.controller;

import com.alibaba.fastjson.JSON;
import com.len.core.shiro.Principal;
import com.len.entity.ProjectWorkerInfo;
import com.len.entity.SysUser;
import com.len.exception.MyException;
import com.len.service.ProjectWorkerInfoService;
import com.len.service.ProjectInfoService;
import com.len.service.SysUserService;
import com.len.util.JsonUtil;
import com.len.util.ReType;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.len.core.annotation.Log;

import java.util.List;

@Controller
@RequestMapping(value = "/projectWorker")

public class ProjectWorkerInfoController {

    @Autowired
    private ProjectWorkerInfoService projectWorkerInfoService;
    @Autowired
    private SysUserService userService;
    @Autowired
    private ProjectInfoService projectInfoService;

    @GetMapping("info")
    public String perWorkerInfo(Model model) {
        String id = Principal.getPrincipal().getId();
        SysUser user = userService.selectByPrimaryKey(id);
        model.addAttribute("user", user);
        return "act/projectWorker/showWorkerInfo";
    }

    @ApiOperation(value = "项目经理下的管理项目", httpMethod = "GET")
    @Log(desc = "管理的项目")
    @GetMapping("infoList")
    @ResponseBody
    public ReType info() {
        List<ProjectWorkerInfo> list = projectWorkerInfoService.selectByPmId(Principal.getPrincipal().getId());
        return new ReType(list.size(), list);
    }

    @GetMapping("showPerInfo")
    public String showProjDetail(Model model, String pmId) {
        ProjectWorkerInfo projectWorkerInfo = projectWorkerInfoService.selectByPrimaryKey(pmId);
        System.out.println(JSON.toJSONString(projectWorkerInfo));
        model.addAttribute("projectInfoPer", projectWorkerInfo);
        return "act/projectWorker/showPerInfo";
    }


}


