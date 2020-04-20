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
import tk.mybatis.mapper.genid.GenId;

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

    public  List<String> listr = new ArrayList<String>(){
        {
            add("dev");
            add("devleader");
            add("test");
            add("testleader");
            add("confman");
            add("qa");
            add("epg");
        }
    };

    public String definFinish(String proId){
        ProjectWorkerInfo wor = new ProjectWorkerInfo();
        ProWorInfoMan proWorInfoMan = new ProWorInfoMan();
        proWorInfoMan.setProId(proId);
        wor.setProId(proId);
        String proStatus="notFinish";
        int flag = 1;
        for(int i=0; i<listr.size(); i++){
            proWorInfoMan.setProRoleName(listr.get(i));
            int resnum = proWorInfoManService.selectRoleNum(proWorInfoMan);
            if(resnum == 0){
                proStatus = "notFinish";
                flag = 0;
            }
        }
        if(flag == 1){
            proStatus = "finished";
        }
        System.out.println("xxx");
        System.out.println(proStatus);
        wor.setProStatus(proStatus);
        projectWorkerInfoService.updateProStatus(wor);
        return proStatus;
    }

    @GetMapping("showInfo")
    public String showInfo(Model model, String proName) {
        String id = Principal.getPrincipal().getId();
        SysUser user = userService.selectByPrimaryKey(id);
        System.out.println(JSON.toJSONString(user));
        model.addAttribute("user", user);
        dealFunction(proName);
        return "act/projectWorker/showWorkerInfo";
    }

    @GetMapping("worInfoMan")
    public String worGroupMan(Model model) {
        return "act/projectWorker/worInfoMan";
    }

    @GetMapping("showProDetail")
    public String showDetail(Model model, String proId) {
        String id = Principal.getPrincipal().getId();
        SysUser user = userService.selectByPrimaryKey(id);
        ProjectWorkerInfo projectWorkerInfo = projectWorkerInfoService.selectByPrimaryKey(proId);
        if(projectWorkerInfo.getProStatus().equals("notFinish")){
            projectWorkerInfo.setProStatus("未完成");
        }
        else{
            projectWorkerInfo.setProStatus("已完成");
        }
        model.addAttribute("baseInfo", projectWorkerInfo);
        return "act/projectWorker/showProDetail";
    }

    public ProWorInfoTmp findProUsername(ProjectWorkerInfo worInf){
        String proId;
        String resU="";
        ProWorInfoMan pwim = new ProWorInfoMan();
        ProWorInfoTmp proWorInfoTmp = new ProWorInfoTmp();
        List<ProWorInfoMan> resUser = new ArrayList<>();
        proId = worInf.getProId();
        proWorInfoTmp.setProId(proId);
        pwim.setProId(proId);
        String proNa = worInf.getProName();
        proWorInfoTmp.setProName(proNa);
        for(int ii=0; ii<listr.size(); ii++){
            resU = "";
            pwim.setProRoleName(listr.get(ii));
            resUser = proWorInfoManService.selectUserByRoleName(pwim);
            if(resUser.size()>0) {
                for (int jj = 0; jj < resUser.size() - 1; jj++) {
                    resU += resUser.get(jj).getUserName();
                    resU += ",";
                }
                resU += resUser.get(resUser.size() - 1).getUserName();
            }
            if(listr.get(ii).equals("dev")){
                proWorInfoTmp.setDev(resU);
            }
            else if(listr.get(ii).equals("devleader")){
                proWorInfoTmp.setDevLeader(resU);
            }
            else if(listr.get(ii).equals("test")){
                proWorInfoTmp.setTest(resU);
            }
            else if(listr.get(ii).equals("testleader")){
                proWorInfoTmp.setTestLeader(resU);
            }
            else if(listr.get(ii).equals("confman")){
                proWorInfoTmp.setConfMan(resU);
            }
            else if(listr.get(ii).equals("qa")){
                proWorInfoTmp.setQa(resU);
            }
            else{
                proWorInfoTmp.setEpg(resU);
            }
        }
        return proWorInfoTmp;
    }

    public ProWorInfoMan transMi(ProWorInfoMan worInf){

        if(worInf.getProRoleName().equals("dev")){
            worInf.setProRoleName("开发人员");
        }
        else if(worInf.getProRoleName().equals("devleader")){
            worInf.setProRoleName("开发负责人");
        }
        else if(worInf.getProRoleName().equals("test")){
            worInf.setProRoleName("测试人员");
        }
        else if(worInf.getProRoleName().equals("testleader")){
            worInf.setProRoleName("测试负责人");
        }
        else if(worInf.getProRoleName().equals("confman")){
            worInf.setProRoleName("配置管理人员");
        }
        else if(worInf.getProRoleName().equals("qa")){
            worInf.setProRoleName("QA");
        }
        else{
            worInf.setProRoleName("EPG");
        }
        return worInf;
    }

    public void dealFunction(String proName){
        ProjectWorkerInfo worInfo = new ProjectWorkerInfo();
        String id = Principal.getPrincipal().getId();
        worInfo.setPmId(id);
        worInfo.setProName(proName);
        String proId;
        List<ProjectWorkerInfo> worInf = projectWorkerInfoService.selectByProName(worInfo);
        for(int j=0; j<worInf.size(); j++){
            proId = worInf.get(j).getProId();
            definFinish(proId);
        }

    }

    @ApiOperation(value = "项目人员信息", httpMethod = "GET")
    @Log(desc = "当前项目经理管理的项目人员信息")
    @GetMapping("info")
    @ResponseBody
    public ReType proWorkerInfo(Model mol, String proName, String page, String limit) {
        String id = Principal.getPrincipal().getId();
        ProjectWorkerInfo worInfo = new ProjectWorkerInfo();
        worInfo.setPmId(id);
        worInfo.setProName(proName);
        List<ProWorInfoTmp> res = new ArrayList<ProWorInfoTmp>();
        List<ProjectWorkerInfo> worInff = projectWorkerInfoService.selectByProName(worInfo);
        for (int i = 0; i < worInff.size(); i++) {
            ProWorInfoTmp proWorInfoTmp = findProUsername(worInff.get(i));
            if (worInff.get(i).getProStatus().equals("notFinish")) {
                proWorInfoTmp.setProStatus("未完成");
            } else {
                proWorInfoTmp.setProStatus("已完成");
            }
            res.add(proWorInfoTmp);
        }
        return new ReType(res.size(), res);
    }

    @ApiOperation(value = "项目开发信息", httpMethod = "GET")
    @Log(desc = "当前项目经理管理的项目人员信息")
    @GetMapping("allWor")
    @ResponseBody
    public ReType allWor(String proId, String page, String limit) {
        List<ProWorInfoMan> worInf = proWorInfoManService.selectByProId(proId);
        List<ProWorInfoMan> res = new ArrayList<ProWorInfoMan>();
        if(worInf.size()>=1) {
            for (int i = 0; i < worInf.size(); i++) {
                res.add(transMi(worInf.get(i)));
            }
        }
        return new ReType(res.size(), res);
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
        if(worInfo.size()>=1) {
            for (int i = 0; i < worInfo.size(); i++) {
                if(worInfo.get(i).getProRoleName().equals("dev")){
                    worInfo.get(i).setProRoleName("开发人员");
                }
                else if(worInfo.get(i).getProRoleName().equals("devleader")){
                    worInfo.get(i).setProRoleName("开发负责人");
                }
                else if(worInfo.get(i).getProRoleName().equals("test")){
                    worInfo.get(i).setProRoleName("测试人员");
                }
                else if(worInfo.get(i).getProRoleName().equals("testleader")){
                    worInfo.get(i).setProRoleName("测试负责人");
                }
                else if(worInfo.get(i).getProRoleName().equals("confman")){
                    worInfo.get(i).setProRoleName("配置管理人员");
                }
                else if(worInfo.get(i).getProRoleName().equals("qa")){
                    worInfo.get(i).setProRoleName("QA");
                }
                else{
                    worInfo.get(i).setProRoleName("EPG");
                }
            }
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
            j.setMsg(msg);
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

    @GetMapping(value = "showUpdProWor")
    public String goUpdProWor(Model model, String id) {
        ProWorInfoMan proWorInfoMan = proWorInfoManService.selectByPrimaryKey(id);
        System.out.println(proWorInfoMan);
        model.addAttribute("currentInfo",proWorInfoMan);
        transMi(proWorInfoMan);
        model.addAttribute("currentInf",proWorInfoMan);
        return "act/projectWorker/updProWorInfo";
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
    public JsonUtil addProWor(String proId, String proName, String userId, String proRoleName){
        JsonUtil j = new JsonUtil();
        String msg="新增项目人员成功";
        if(proRoleName.equals("devleader")||proRoleName.equals("testleader")||proRoleName.equals("qa")||proRoleName.equals("confman")||proRoleName.equals("epg")){
            ProWorInfoMan proWor = new ProWorInfoMan();
            proWor.setProId(proId);
            proWor.setProRoleName(proRoleName);
            proWor.setUserId(userId);
            int resnum = proWorInfoManService.selectRoleNum(proWor);
            if(resnum == 1){
                msg = "新增项目人员失败,该角色下已有人员";
                j.setFlag(false);
                j.setMsg(msg);
                return j;
            }
        }
        String id = UUID.randomUUID().toString();
        ProWorInfoMan proWorInfoMan = new ProWorInfoMan();
        proWorInfoMan.setId(id);
        proWorInfoMan.setProId(proId);
        proWorInfoMan.setUserId(userId);
        proWorInfoMan.setProRoleName(proRoleName);
        int sameUser = proWorInfoManService.selectSameCondi(proWorInfoMan);
        System.out.println(sameUser);
        if(sameUser >= 1){
            msg = "新增项目人员失败,该人员已经在此项目承担了该角色";
            j.setFlag(false);
            j.setMsg(msg);
            return j;
        }
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
        String proStatus;
        try{
            proWorInfoManService.insertProWor(proWorInfoMan);
        }catch (MyException e) {
            msg = "新增项目人员失败";
            j.setFlag(false);
            j.setMsg(msg);
            e.printStackTrace();
        }
        j.setFlag(true);
        j.setMsg(msg);
        return j;
    }

    @ApiOperation(value = "修改项目人员信息", httpMethod = "POST")
    @Log(desc = "修改项目人员信息")
    @PostMapping("updProWor")
    @ResponseBody
    public JsonUtil updProWor(String id, String proId, String proRoleName) {
        JsonUtil j = new JsonUtil();
        String msg = "项目人员信息修改成功";
        ProWorInfoMan proWorInfoMan = new ProWorInfoMan();
        proWorInfoMan.setProId(proId);
        proWorInfoMan.setId(id);
        String proStatus;
        proWorInfoMan.setProRoleName(proRoleName);
        int resnum = proWorInfoManService.selectRoleNum(proWorInfoMan);
        if (proRoleName.equals("devleader") || proRoleName.equals("testleader") || proRoleName.equals("qa") || proRoleName.equals("confman") || proRoleName.equals("epg")) {
            if (resnum >= 1) {
                msg = "修改项目人员失败,该角色下已有人员";
                j.setFlag(false);
                j.setMsg(msg);
                return j;
            } else {
                try {
                    proWorInfoManService.updateRoleById(proWorInfoMan);
                } catch (MyException e) {
                    msg = "修改项目人员信息失败";
                    j.setFlag(false);
                    j.setMsg(msg);
                    e.printStackTrace();
                }
                j.setFlag(true);
                j.setMsg(msg);
                return j;
            }
        } else {
            try {
                proWorInfoManService.updateRoleById(proWorInfoMan);
            } catch (MyException e) {
                msg = "修改项目人员信息失败";
                j.setFlag(false);
                j.setMsg(msg);
                e.printStackTrace();
            }
            j.setFlag(true);
            j.setMsg(msg);
            return j;
        }
    }


}


