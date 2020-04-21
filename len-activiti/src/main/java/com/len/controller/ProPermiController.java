package com.len.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.len.core.annotation.Log;
import com.len.core.shiro.Principal;
import com.len.entity.*;
import com.len.exception.MyException;
import com.len.service.*;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/permission")
public class ProPermiController {

    @Autowired
    private ProjectWorkerInfoService projectWorkerInfoService;

    @Autowired
    private ProWorInfoManService proWorInfoManService;

    @Autowired
    private ProPermissionService proPermissionService;

    //000为项目经理
    public String dealrole(String role) {
        String res="";
        if(role.equals("PM")||role.equals("pm")){
            res = "000";
            return res;
        } else if (role.equals("devleader")) {
            res = "001";
            return res;
        } else if (role.equals("dev")) {
            res = "002";
            return res;
        } else if (role.equals("testleader")) {
            res = "003";
            return res;
        } else if (role.equals("test")) {
            res = "004";
            return res;
        } else if (role.equals("confman")) {
            res = "005";
            return res;
        } else if (role.equals("qa")||role.equals("QA")) {
            res = "006";
            return res;
        } else if(role.equals("epg")||role.equals("EPG")){
            res = "007";
            return res;
        }
        return res;
    }

    public static final List<String> listsr = new ArrayList<String>() {
        {
            add("000");
            add("001");
            add("002");
            add("003");
            add("004");
            add("005");
            add("006");
            add("007");
        }
    };

    public void roleTrans(ProPermission proPermission) {
        String proRoleName = proPermission.getProRoleName();
        String res = "";
        for (int i = 0; i < listsr.size(); i++) {
            if (proRoleName.indexOf(listsr.get(i)) >= 0) {
                if( i==0){
                    res += "项目经理,";
                } else if (i == 1) {
                    res += "开发负责人,";
                } else if (i == 2) {
                    res += "开发人员,";
                } else if (i == 3) {
                    res += "测试负责人,";
                } else if (i == 4) {
                    res += "测试人员,";
                } else if (i == 5) {
                    res += "配置管理人员,";
                } else if (i == 6) {
                    res += "QA,";
                } else if(i == 7){
                    res += "EPG,";
                }
            }
        }
        System.out.println(proPermission);
        System.out.println(res);
        proPermission.setProRoleName(res);
    }

    //预先工作,得到当前项目经理完成项目人员角色设定的项目
    public void dealPro(String proId) {
        ProPermission proPermission = new ProPermission();
        ProjectWorkerInfo projectWorkerInfo = projectWorkerInfoService.selectByProId(proId);
        proPermission.setProId(proId);
        proPermission.setProName(projectWorkerInfo.getProName());
        // 找到该项目下的项目经理id
        String pmId = projectWorkerInfo.getPmId();
        proPermission.setPmId(pmId);
        proPermission.setPmName(projectWorkerInfo.getPmName());
        // 找到该项目下的所有工作人员
        List<ProWorInfoMan> proWorInfoManList = proWorInfoManService.selectUserByProId(proId);
        for (int i = 0; i < proWorInfoManList.size(); i++) {
            ProWorInfoMan proWorInfoMan = new ProWorInfoMan();
            proWorInfoMan.setProId(proId);
            String role = "";
            String userId = proWorInfoManList.get(i).getUserId();
            String userName = proWorInfoManList.get(i).getUserName();
            // 不在则添加,在则检查是否需要修改
            proPermission.setUserId(userId);
            proPermission.setUserName(userName);
            int num = proPermissionService.selectNotInNum(proPermission);
            proWorInfoMan.setUserId(userId);
            // 找到该人员的所有角色
            List<ProWorInfoMan> proWorInfoManList1 = proWorInfoManService.selectRoleByUserId(proWorInfoMan);
            for (int j = 0; j < proWorInfoManList1.size(); j++) {
                //将该人员的所有角色以字符串的形式串起来存到权限表
                role += dealrole(proWorInfoManList1.get(j).getProRoleName());
            }
            // 将该人员的角色都存到权限表里去
            proPermission.setProRoleName(role);
            if (num != 0) {
                //在则判断是否需要update,角色是否发生变化
                ProPermission permis = new ProPermission();
                permis = proPermissionService.selectUserInfoByPidUid(proPermission);
                if (permis.getProRoleName().equals(proPermission.getProRoleName())) {
                    continue;
                } else {
                    proPermissionService.updateUserInfoByPidUid(proPermission);
                }
            } else {
                //不在则添加
                String id = UUID.randomUUID().toString();
                proPermission.setId(id);
                proPermissionService.insertNew(proPermission);
            }
        }
    }

    // 预处理已完成但还不在权限表里的项目人员,在权限表而不在项目人员表里时则项目经理可以手动删掉
    // 在项目人员表而不在权限表里则添加
    // 两张表都在的,检查用户角色是否和之前的一致,不一致则update
    public void preWork() {
        String id = Principal.getPrincipal().getId();
        List<ProjectWorkerInfo> projectWorkerInfos = projectWorkerInfoService.selectByPmId(id);
        for (int i = 0; i < projectWorkerInfos.size(); i++) {
            String proId = projectWorkerInfos.get(i).getProId();
            dealPro(proId);
        }
    }

    @GetMapping("show")
    public String submit(Model model) {
        preWork();
        return "act/permission";
    }

    @ApiOperation(value = "项目展示", httpMethod = "GET")
    @Log(desc = "当前用户参与的项目人员信息")
    @GetMapping("perManList")
    @ResponseBody
    public ReType perManList(String proName, String page, String limit) {
        String id = Principal.getPrincipal().getId();
        ProPermission proPermission = new ProPermission();
        proPermission.setProName(proName);
        proPermission.setPmId(id);
        List<ProPermission> proPermissions = proPermissionService.selectByPmIdPName(proPermission);
        for (int j = 0; j < proPermissions.size(); j++) {
            roleTrans(proPermissions.get(j));
        }
        return new ReType(proPermissions.size(), proPermissions);
    }

    @ApiOperation(value = "开通项目人员权限", httpMethod = "POST")
    @Log(desc = "开通项目人员权限")
    @PostMapping("addPermission")
    @ResponseBody
    public JsonUtil addPermission(String permission, String id) {
        JsonUtil j = new JsonUtil();
        String msg = "权限开通成功";
        ProPermission proPermission = new ProPermission();
        proPermission.setId(id);
        proPermission.addPermission(permission);
        try {
            proPermissionService.updPerm(proPermission);
        } catch (MyException e) {
            msg = "权限开通失败";
            j.setFlag(false);
            j.setMsg(msg);
            e.printStackTrace();
            return j;
        }
        j.setFlag(true);
        j.setMsg(msg);
        return j;
    }

    @ApiOperation(value = "取消项目人员权限", httpMethod = "POST")
    @Log(desc = "取消项目人员权限")
    @PostMapping("cancelPermission")
    @ResponseBody
    public JsonUtil cancelPermission(String permission, String id) {
        JsonUtil j = new JsonUtil();
        String msg = "权限取消成功";
        ProPermission proPermission = new ProPermission();
        proPermission.setId(id);
        proPermission.cancelPermission(permission);
        try {
            proPermissionService.updPerm(proPermission);
        } catch (MyException e) {
            msg = "权限取消失败";
            j.setFlag(false);
            j.setMsg(msg);
            e.printStackTrace();
            return j;
        }
        j.setFlag(true);
        j.setMsg(msg);
        return j;
    }

    @ApiOperation(value = "删除项目人员", httpMethod = "POST")
    @Log(desc = "删除项目人员")
    @PostMapping("delWorPermi")
    @ResponseBody
    public JsonUtil delWorPermi(String id) {
        JsonUtil j = new JsonUtil();
        String msg = "人员删除成功";
        try {
            proPermissionService.deleteById(id);
        } catch (MyException e) {
            msg = "人员删除失败";
            j.setFlag(false);
            j.setMsg(msg);
            e.printStackTrace();
            return j;
        }
        j.setFlag(true);
        j.setMsg(msg);
        return j;
    }


}
