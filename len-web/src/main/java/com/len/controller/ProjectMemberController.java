package com.len.controller;

import com.alibaba.fastjson.JSON;
import com.len.core.annotation.Log;
import com.len.core.shiro.Principal;
import com.len.entity.ProWorInfoMan;
import com.len.entity.ProjectInfo;
import com.len.entity.RskInfo;
import com.len.qo.ProjectMemberDetail;
import com.len.service.ProWorInfoManService;
import com.len.service.ProjectInfoService;
import com.len.service.RiskInfoService;
import com.len.service.SysUserService;
import com.len.util.ReType;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

//import com.len.entity.ProReUsr;

@Controller
@RequestMapping(value = "/proMember")
public class ProjectMemberController {

    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private SysUserService userService;

    @Autowired
    private ProWorInfoManService proWorInfoManService;

    @Autowired
    private RiskInfoService riskInfoService;


    @ApiOperation(value = "项目成员", httpMethod = "GET")
    @Log(desc = "获取项目成员等信息")
    @GetMapping("showProjectList")
    @ResponseBody
    public ReType ProjectList() {
        List<ProjectMemberDetail> lists = new ArrayList<>();
        List<ProWorInfoMan> proWorInfoManList = proWorInfoManService.selectByUId(Principal.getPrincipal().getId());

        for (ProWorInfoMan proWorInfoMan : proWorInfoManList) {
            System.out.println("一轮:");
            System.out.println(proWorInfoMan.getProId());
            ProjectInfo projectInfo = projectInfoService.selectByPrimaryKey(proWorInfoMan.getProId());
            System.out.println(projectInfo.getId());

            if (projectInfo.getProjState().equals("进行中")) {
                List<ProWorInfoMan> proWorInfoMans = proWorInfoManService.selectByPId(projectInfo.getId());

                ProjectMemberDetail projectMemberDetail = new ProjectMemberDetail(
                        projectInfo.getId(), projectInfo.getPmId(), projectInfo.getPmName(), projectInfo.getProjCustomer(),
                        projectInfo.getProjName(), projectInfo.getProjNo(), projectInfo.getProjState(), proWorInfoMans
                );
                lists.add(projectMemberDetail);
            }
        }

        return new ReType(lists.size(), lists);
    }

    @ApiOperation(value = "指定项目成员", httpMethod = "GET")
    @Log(desc = "指定项目成员等信息")
    @GetMapping("showMemberList")
    @ResponseBody
    public ReType MemberList(String riskId) {
        RskInfo rskInfo = riskInfoService.selectByPrimaryKey(riskId);
        System.out.println(JSON.toJSONString(rskInfo));
        List<ProWorInfoMan> lists = proWorInfoManService.selectByPId(rskInfo.getpId());

        return new ReType(lists.size(), lists);
    }

}