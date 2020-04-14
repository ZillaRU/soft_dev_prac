package com.len.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.len.core.shiro.Principal;
import com.len.entity.*;
import com.len.exception.MyException;
import com.len.service.ProWorInfoManService;
import com.len.service.ProjectInfoService;
import com.len.service.ProjectWorkerInfoService;
import com.len.service.SysUserService;
import com.len.util.JsonUtil;
import org.activiti.engine.RepositoryService;
import com.len.util.ReType;
import java.util.UUID;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    private ProjectInfoService projectInfoService;

    @Autowired
    private ProWorInfoManService proWorInfoManService;

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

    @GetMapping("worInfoMan")
    public String devGroupMan(Model model) {
        return "act/projectWorker/worInfoMan";
    }

    @GetMapping("showProDetail")
    public String showDetail(Model model, String proId) {
        String id = Principal.getPrincipal().getId();
        SysUser user = userService.selectByPrimaryKey(id);
        ProjectWorkerInfo projectWorkerInfo = projectWorkerInfoService.selectByPrimaryKey(proId);
        model.addAttribute("baseInfo", projectWorkerInfo);
        return "act/projectWorker/showProDetail";
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
        return new ReType(worInf.size(), worInf);
    }

    @ApiOperation(value = "项目人员信息", httpMethod = "GET")
    @Log(desc = "当前项目经理管理的项目人员开发信息")
    @GetMapping("worInfo")
    @ResponseBody
    public ReType proWorInfo(Model mol, String proName, ProWorInfoMan proWorInfoMan) {
        String id= Principal.getPrincipal().getId();
        proWorInfoMan.setPmId(id);
        proWorInfoMan.setProName(proName);
        List<ProWorInfoMan> worInfo = proWorInfoManService.selectByProName(proWorInfoMan);
        List<ProWorInfoMan> res = new ArrayList<>();
        for(int i=0; i<worInfo.size(); i++)
        {
            worInfo.get(i).setProRoleName(worInfo.get(i).getProRoleId());
            res.add(worInfo.get(i));
        }
        return new ReType(worInfo.size(), worInfo);
    }

    @ApiOperation(value = "删除项目人员", httpMethod = "POST")
    @Log(desc = "删除项目人员")
    @PostMapping("delWor")
    @ResponseBody
    public JsonUtil delWor(String id){
        JsonUtil j = new JsonUtil();
        String msg="删除成功";
        try{
            proWorInfoManService.delById(id);
        }catch (MyException e) {
            msg = "删除失败";
            j.setFlag(false);
            e.printStackTrace();
        }
        j.setFlag(true);
        j.setMsg(msg);
        return j;
    }

    @GetMapping(value = "showAddProWor")
    public String goAddProWor(Model model) {
        return "act/projectWorker/addProWorInfo";
    }

    @PostMapping(value = "showAllPro")
    @ResponseBody
    public String showAllPro() {
        JSONObject returnValue = new JSONObject();
        String id = Principal.getPrincipal().getId();
        List<ProjectInfo> projectInfos = projectInfoService.selectByPmId(id);
        returnValue.put("projs", projectInfos);
        return JSON.toJSONString(returnValue);
    }

    @ApiOperation(value = "新增项目人员", httpMethod = "POST")
    @Log(desc = "新增项目人员")
    @PostMapping("addProWor")
    @ResponseBody
    public JsonUtil addProWor(String proId, String proName, String userId, int proRoleId){
        JsonUtil j = new JsonUtil();
        String msg="新增项目人员成功";
        String id = UUID.randomUUID().toString();
        ProWorInfoMan proWorInfoMan = new ProWorInfoMan();
        proWorInfoMan.setId(id);
        proWorInfoMan.setProId(proId);
        proWorInfoMan.setUserId(userId);
        proWorInfoMan.setProRoleId(proRoleId);
        String pmId = Principal.getPrincipal().getId();
        proWorInfoMan.setPmId(pmId);
        SysUser sysUser = userService.selectByPrimaryKey(userId);
        String userName = sysUser.getUsername();
        String email = sysUser.getEmail();
        String phone = sysUser.getPhone();
        proWorInfoMan.setUserName(userName);
        proWorInfoMan.setUserEmail(email);
        proWorInfoMan.setUserPhone(phone);
        proWorInfoMan.setProName(proName);
        try{
            proWorInfoManService.insertProWor(proWorInfoMan);
        }catch (MyException e) {
            msg = "新增项目人员失败";
            j.setFlag(false);
            e.printStackTrace();
        }
        j.setFlag(true);
        j.setMsg(msg);
        return j;
    }


}


