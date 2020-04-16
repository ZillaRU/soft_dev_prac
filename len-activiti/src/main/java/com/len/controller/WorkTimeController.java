package com.len.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.len.core.shiro.Principal;
import com.len.entity.*;
import com.len.exception.MyException;
import com.len.service.*;
import com.len.util.JsonUtil;
import org.activiti.engine.RepositoryService;
import com.len.util.ReType;

import java.util.AbstractList;
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
@RequestMapping(value = "/workTime")

public class WorkTimeController {
    @Autowired
    private ProWorInfoManService proWorInfoManService;

    @Autowired
    private ProjectWorkerInfoService projectWorkerInfoService;

    @Autowired
    private WorkTimeInfoService workTimeInfoService;

    @Autowired
    private SysUserService userService;

    public  List<String> listr = new ArrayList<String>(){
        {
            add("dev");
            add("test");
            add("devleader");
            add("testleader");
            add("confman");
            add("qa");
            add("epg");
        }
    };

    // 预先处理项目中人员之间的审批和被审批之间的关系
    // epg,qa,配置管理员由项目经理审批,开发人员和测试人员由开发负责人和测试负责人审批
    // 当项目中的某成员的上级是自己时，则不需要向自己提交审批,例如某成员既是开发人员又是开发负责人时，则只由项目经理审批
    // 排除当一个成员担任多个leader时重复向项目经理发送工时信息，做去重处理
    public void preFunction(String proId){
        workTimeInfoService.delByProId(proId);
        ProjectWorkerInfo projectWorkerInfo = projectWorkerInfoService.selectByPrimaryKey(proId);
        String proName = projectWorkerInfo.getProName();
        String pmId = projectWorkerInfo.getPmId();
        String pmName = projectWorkerInfo.getPmName();
        WorkTimeInfo workTimeInfo = new WorkTimeInfo();
        workTimeInfo.setProId(proId);
        workTimeInfo.setProName(proName);
        ProWorInfoMan proWorInfoMan = new ProWorInfoMan();
        proWorInfoMan.setProId(proId);
        List<String> leaderids = new ArrayList<>();
        List<String> resleaderids = new ArrayList<>();
        String id;
        // 先插入leader信息,leader提交给项目经理,排除同一人担任多份leader的情况
        for(int i=2; i<listr.size(); i++){
            proWorInfoMan.setProRoleName(listr.get(i));
            String insertid = proWorInfoManService.selectUserByRoleName(proWorInfoMan).get(0).getUserId();
            leaderids.add(insertid);
            if(resleaderids.contains(insertid)){
                continue;
            }
            // 如果说leader又是项目经理的话,就不用提交
            else if(insertid.equals(pmId)){
                continue;
            }
            else{
                resleaderids.add(insertid);
                String insertname = proWorInfoManService.selectUserByRoleName(proWorInfoMan).get(0).getUserName();
                workTimeInfo.setSendUserName(insertname);
                workTimeInfo.setSendUserId(insertid);
                workTimeInfo.setReceiveUserName(pmName);
                workTimeInfo.setReceiveUserId(pmId);
                id = UUID.randomUUID().toString();
                workTimeInfo.setId(id);
                workTimeInfoService.insertWorkTimeInfo(workTimeInfo);
            }
        }
        //考虑开发人员
        proWorInfoMan.setProRoleName(listr.get(0));
        List<ProWorInfoMan> resList = proWorInfoManService.selectUserByRoleName(proWorInfoMan);
        proWorInfoMan.setProRoleName(listr.get(2));
        String devleadername = proWorInfoManService.selectUserByRoleName(proWorInfoMan).get(0).getUserName();
        String devleaderid = proWorInfoManService.selectUserByRoleName(proWorInfoMan).get(0).getUserId();
        for(int j=0; j<resList.size(); j++){
            //排除开发lerder是开发人员的情况
            if(resList.get(j).getUserId().equals(devleaderid)){
                continue;
            }
            else{
                String insertname = resList.get(j).getUserName();
                String insertid = resList.get(j).getUserId();
                workTimeInfo.setSendUserName(insertname);
                workTimeInfo.setSendUserId(insertid);
                workTimeInfo.setReceiveUserName(devleadername);
                workTimeInfo.setReceiveUserId(devleaderid);
                id = UUID.randomUUID().toString();
                workTimeInfo.setId(id);
                workTimeInfoService.insertWorkTimeInfo(workTimeInfo);
            }
        }
        //考虑测试人员
        proWorInfoMan.setProRoleName(listr.get(1));
        resList = proWorInfoManService.selectUserByRoleName(proWorInfoMan);
        proWorInfoMan.setProRoleName(listr.get(3));
        String testleadername = proWorInfoManService.selectUserByRoleName(proWorInfoMan).get(0).getUserName();
        String testleaderid = proWorInfoManService.selectUserByRoleName(proWorInfoMan).get(0).getUserId();
        for(int j=0; j<resList.size(); j++){
            //排除测试lerder是测试人员的情况
            if(resList.get(j).getUserId().equals(testleaderid)){
                continue;
            }
            else{
                String insertname = resList.get(j).getUserName();
                String insertid = resList.get(j).getUserId();
                workTimeInfo.setSendUserName(insertname);
                workTimeInfo.setSendUserId(insertid);
                workTimeInfo.setReceiveUserName(testleadername);
                workTimeInfo.setReceiveUserId(testleaderid);
                id = UUID.randomUUID().toString();
                workTimeInfo.setId(id);
                workTimeInfoService.insertWorkTimeInfo(workTimeInfo);
            }
        }
    }

    @GetMapping("submit")
    public String submit(Model model) {
        return "workTime/selProSub";
    }

    @ApiOperation(value = "项目展示", httpMethod = "GET")
    @Log(desc = "当前用户参与的项目人员信息")
    @GetMapping("proWorRla")
    @ResponseBody
    public ReType proWorRla(String page, String limit) {
        String id= Principal.getPrincipal().getId();
        List<ProWorInfoMan> proWorInfoMan = proWorInfoManService.selectMyProIds(id);
        String prId;
        String proSta;
        // 排除当前用户参与的未完成导入人员的项目
        for(int i=0; i<proWorInfoMan.size(); i++){
            ProjectWorkerInfo prow;
            prId = proWorInfoMan.get(i).getProId();
            prow = projectWorkerInfoService.selectByProId(prId);
            proSta = prow.getProStatus();
            if(proSta.equals("notFinish")){
                continue;
            }
            else{
                preFunction(prId);
            }
        }
        return new ReType(proWorInfoMan.size(), proWorInfoMan);
    }


}
