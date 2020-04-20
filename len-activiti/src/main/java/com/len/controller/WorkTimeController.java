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

    @Autowired
    private ProjFuncService projFuncService;

    @Autowired
    private WorkTimeInfoDetailService workTimeInfoDetailService;

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

    public  List<String> activ_type = new ArrayList<String>(){
        {
            add("工程活动");
            add("管理活动");
            add("外包活动");
            add("支持活动");
        }
    };

    public  List<String> activ_group_one = new ArrayList<String>(){
        {
            add("需求开发");
            add("设计");
            add("编码");
            add("单元测试");
            add("集成测试");
            add("系统测试");
            add("交付");
            add("维护");
        }
    };

    public  List<String> activ_group_two = new ArrayList<String>(){
        {
            add("范围管理");
            add("计划调整");
            add("监控与分析");
            add("联络与沟通");
        }
    };

    public  List<String> activ_group_three = new ArrayList<String>(){
        {
            add("外包管理");
            add("外包验收");
            add("外包支持");
        }
    };

    public  List<String> activ_group_four = new ArrayList<String>(){
        {
            add("配置管理");
            add("QA审计");
            add("会议报告总结");
            add("培训");
            add("其他");
        }
    };

    public String trans(char a, char b){
        String res = "";
        int aa = Integer.parseInt(String.valueOf(a));
        int bb = Integer.parseInt(String.valueOf(b));
        if(aa==0){
            res += activ_type.get(aa) + ">" + activ_group_one.get(bb);
        }else if(aa==1){
            res += activ_type.get(aa) + ">" + activ_group_two.get(bb);
        }else if(aa==2){
            res += activ_type.get(aa) + ">" + activ_group_three.get(bb);
        }else{
            res += activ_type.get(aa) + ">" + activ_group_four.get(bb);
        }
        return res;
    }

    public void sendDealStatus(WorkTimeInfoDetail workTimeInfoDetail){
        if(workTimeInfoDetail.getInfoStatus().equals("processing")){
            workTimeInfoDetail.setInfoStatus("处理中");
        }
        else if(workTimeInfoDetail.getInfoStatus().equals("refused")){
            workTimeInfoDetail.setInfoStatus("已驳回");
        }
        else if(workTimeInfoDetail.getInfoStatus().equals("accepted")){
            workTimeInfoDetail.setInfoStatus("已通过");
        }
    }

    public void receiveDealStatus(WorkTimeInfoDetail workTimeInfoDetail){
        if(workTimeInfoDetail.getInfoStatus().equals("processing")){
            workTimeInfoDetail.setInfoStatus("未处理");
        }
        else if(workTimeInfoDetail.getInfoStatus().equals("refused")){
            workTimeInfoDetail.setInfoStatus("已驳回");
        }
        else if(workTimeInfoDetail.getInfoStatus().equals("accepted")){
            workTimeInfoDetail.setInfoStatus("已确认");
        }
    }

    // 预先处理项目中人员之间的审批和被审批之间的关系
    // epg,qa,配置管理员由项目经理审批,开发人员和测试人员由开发负责人和测试负责人审批
    // 当项目中的某成员的上级是自己时，则不需要向自己提交审批,例如某成员既是开发人员又是开发负责人时，则只由项目经理审批
    // 排除当一个成员担任多个leader时重复向项目经理发送工时信息，做去重处理
    public void preFunction(String proId) {
        workTimeInfoService.delByProId(proId);
        ProjectWorkerInfo projectWorkerInfo = projectWorkerInfoService.selectByPrimaryKey(proId);
        String proName = projectWorkerInfo.getProName();
        String pmId = projectWorkerInfo.getPmId();
        String pmName = projectWorkerInfo.getPmName();
        WorkTimeInfo workTimeInfo = new WorkTimeInfo();
        workTimeInfo.setProId(proId);
        workTimeInfo.setProName(proName);
        workTimeInfo.setPmId(pmId);
        workTimeInfo.setPmName(pmName);
        ProWorInfoMan proWorInfoMan = new ProWorInfoMan();
        proWorInfoMan.setProId(proId);
        List<String> workerids = new ArrayList<>();
        List<String> resworkerids = new ArrayList<>();
        List<String> leaderids = new ArrayList<>();
        List<String> resleaderids = new ArrayList<>();
        List<String> workernames = new ArrayList<>();
        List<String> resworkernames = new ArrayList<>();
        List<String> leadernames = new ArrayList<>();
        List<String> resleadernames = new ArrayList<>();
        List<String> respair = new ArrayList<>();
        String mypair;
        String id;
        for (int i = 0; i < listr.size(); i++) {
            proWorInfoMan.setProRoleName(listr.get(i));
            List<ProWorInfoMan> resList = proWorInfoManService.selectUserByRoleName(proWorInfoMan);
            if (i == 2 || i == 3 || i == 4 || i == 5 || i == 6) {
                workerids.add(resList.get(0).getUserId());
                workernames.add(resList.get(0).getUserName());
                leaderids.add(pmId);
                leadernames.add(pmName);
            } else if (i == 0) {
                ProWorInfoMan proWorInfoMan1 = new ProWorInfoMan();
                proWorInfoMan1.setProId(proId);
                proWorInfoMan1.setProRoleName(listr.get(2));
                for (int j = 0; j < resList.size(); j++) {
                    leaderids.add(proWorInfoManService.selectUserByRoleName(proWorInfoMan1).get(0).getUserId());
                    leadernames.add(proWorInfoManService.selectUserByRoleName(proWorInfoMan1).get(0).getUserName());
                    workerids.add(resList.get(j).getUserId());
                    workernames.add(resList.get(j).getUserName());
                }
            } else if (i == 1) {
                ProWorInfoMan proWorInfoMan2 = new ProWorInfoMan();
                proWorInfoMan2.setProId(proId);
                proWorInfoMan2.setProRoleName(listr.get(3));
                for (int k = 0; k < resList.size(); k++) {
                    leaderids.add(proWorInfoManService.selectUserByRoleName(proWorInfoMan2).get(0).getUserId());
                    leadernames.add(proWorInfoManService.selectUserByRoleName(proWorInfoMan2).get(0).getUserName());
                    workerids.add(resList.get(k).getUserId());
                    workernames.add(resList.get(k).getUserName());
                }
            }
        }
        // 排除上级是自己的情况
        for (int ii = 0; ii < workerids.size(); ii++) {
            // 上级是自己
            if (workerids.get(ii).equals(leaderids.get(ii))) {
                continue;
            } else {
                // 排除leader和worker重复的情况
                mypair = workerids.get(ii)+ leaderids.get(ii);
                if(respair.contains(mypair)){
                    continue;
                }
                else {
                    respair.add(mypair);
                    resleaderids.add(leaderids.get(ii));
                    resleadernames.add(leadernames.get(ii));
                    resworkerids.add(workerids.get(ii));
                    resworkernames.add(workernames.get(ii));
                }
            }
        }
        for(int jj=0; jj<resleaderids.size();jj++){
            id = UUID.randomUUID().toString();
            workTimeInfo.setId(id);
            workTimeInfo.setSendUserId(resworkerids.get(jj));
            workTimeInfo.setSendUserName(resworkernames.get(jj));
            workTimeInfo.setReceiveUserId(resleaderids.get(jj));
            workTimeInfo.setReceiveUserName(resleadernames.get(jj));
            workTimeInfoService.insertWorkTimeInfo(workTimeInfo);
        }
    }

    public void preDeal(){
        String id= Principal.getPrincipal().getId();
        ProWorInfoMan pw = new ProWorInfoMan();
        pw.setUserId(id);
        List<ProWorInfoMan> proWorInfoMan = proWorInfoManService.selectMyProIds(pw);
        String prId;
        String proSta;
        // 排除当前用户参与的未完成导入人员的项目
        for(int i=0; i<proWorInfoMan.size(); i++) {
            ProjectWorkerInfo prow;
            prId = proWorInfoMan.get(i).getProId();
            prow = projectWorkerInfoService.selectByProId(prId);
            proSta = prow.getProStatus();
            if (proSta.equals("notFinish")) {
                workTimeInfoService.delByProId(prId);
                continue;
            } else {
                preFunction(prId);
            }
        }
    }

    @GetMapping("submit")
    public String submit(Model model) {
        preDeal();
        return "workTime/selProSub";
    }

    @ApiOperation(value = "项目展示", httpMethod = "GET")
    @Log(desc = "当前用户参与的项目人员信息")
    @GetMapping("proWorRla")
    @ResponseBody
    public ReType proWorRla(String page, String limit) {
        // 排除当前用户参与的未完成导入人员的项目
        String id= Principal.getPrincipal().getId();
        WorkTimeInfo workTimeInfo = new WorkTimeInfo();
        workTimeInfo.setSendUserId(id);
        List<WorkTimeInfo> workTimeInfos = workTimeInfoService.selectByUserId(workTimeInfo);
        return new ReType(workTimeInfos.size(), workTimeInfos);
    }

    @GetMapping("showProRoleDetail")
    @Log(desc="显示当前选择项目的角色分配信息")
    public String showProRoleDetail(Model model, String proId, String proName, String pmName){
        String resU;
        ProWorInfoMan pwim = new ProWorInfoMan();
        List<ProWorInfoMan> resUser = new ArrayList<>();
        ProWorInfoTmp proWorInfoTmp = new ProWorInfoTmp();
        proWorInfoTmp.setProId(proId);
        proWorInfoTmp.setProName(proName);
        proWorInfoTmp.setPmName(pmName);
        pwim.setProId(proId);
        for(int ii=0; ii<listr.size(); ii++) {
            resU = "";
            pwim.setProRoleName(listr.get(ii));
            resUser = proWorInfoManService.selectUserByRoleName(pwim);

            for (int jj = 0; jj < resUser.size() - 1; jj++) {
                resU += resUser.get(jj).getUserName();
                resU += ",";
            }
            resU += resUser.get(resUser.size() - 1).getUserName();
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
        model.addAttribute("proRoleOb",proWorInfoTmp);
        return "workTime/showProRoleInfo";
    }

     @GetMapping(value = "submitWorkTimeInfo")
     public String submitWorkTimeInfo(Model model, String id) {
         WorkTimeInfo workTimeInfo = workTimeInfoService.selectById(id);
         String projId = workTimeInfo.getProId();
         List<ProjectFunction> projectFunctions = projFuncService.selectByProjId(projId);
         model.addAttribute("funcs",projectFunctions);
         model.addAttribute("baseInfo",workTimeInfo);
         return "workTime/subWorkTimeInfo";
    }

    @PostMapping(value = "allActiv")
    @ResponseBody
    public String allActiv() {
        JSONObject returnValue = new JSONObject();
        returnValue.put("activ_type", activ_type);
        returnValue.put("engi_activ", activ_group_one);
        returnValue.put("mana_activ", activ_group_two);
        returnValue.put("out_activ", activ_group_three);
        returnValue.put("supp_activ", activ_group_four);
        return JSON.toJSONString(returnValue);
    }

    @GetMapping(value = "allFuncs")
    @Log(desc = "当前项目的功能列表获取")
    @ResponseBody
    public String allFuncs(String proId) {
        JSONObject returnValue = new JSONObject();
        List<ProjectFunction> projectFunctions= projFuncService.selectByProjId(proId);
        System.out.println(projectFunctions);
        returnValue.put("projs", projectFunctions);
        return JSON.toJSONString(returnValue);
    }

    @ApiOperation(value = "提交工时信息", httpMethod = "POST")
    @Log(desc = "提交工时信息")
    @PostMapping("submWorInfo")
    @ResponseBody
    public JsonUtil addProWor(String submitDate,  String startTime, String endTime,
                              String funcId, String funcName,
                              String activId,
                              String note, String proName, String proId, String pmName,
                              String receiveUserId, String receiveUserName,
                              String sendUserId, String sendUserName){
        JsonUtil j = new JsonUtil();
        String msg="工时信息提交成功";
        WorkTimeInfoDetail workTimeInfoDetail = new WorkTimeInfoDetail();
        workTimeInfoDetail.setSubmitDate(submitDate);
        workTimeInfoDetail.setStartTime(startTime);
        workTimeInfoDetail.setEndTime(endTime);
        workTimeInfoDetail.setFuncId(funcId);
        workTimeInfoDetail.setFuncName(funcName);
        workTimeInfoDetail.setActivId(activId);
        workTimeInfoDetail.setNote(note);
        workTimeInfoDetail.setProName(proName);
        workTimeInfoDetail.setProId(proId);
        workTimeInfoDetail.setPmName(pmName);
        workTimeInfoDetail.setReceiveUserId(receiveUserId);
        workTimeInfoDetail.setReceiveUserName(receiveUserName);
        workTimeInfoDetail.setSendUserId(sendUserId);
        workTimeInfoDetail.setSendUserName(sendUserName);
        int hasSub = workTimeInfoDetailService.selectHasSubmitted(workTimeInfoDetail);
        if(hasSub != 0){
            msg = "请勿重复提交";
            j.setFlag(false);
            j.setMsg(msg);
            return j;
        }
        String id = UUID.randomUUID().toString();
        workTimeInfoDetail.setId(id);
        String info_status = "processing";
        workTimeInfoDetail.setInfoStatus(info_status);
        try{
            workTimeInfoDetailService.insertWorkTimeInfo(workTimeInfoDetail);
        }catch (MyException e) {
            msg = "工时信息提交失败";
            j.setFlag(false);
            j.setMsg(msg);
            e.printStackTrace();
        }
        j.setFlag(true);
        j.setMsg(msg);
        return j;
    }

    @GetMapping("/report")
    public String report(Model model) {
        return "workTime/mySubList";
    }

    @ApiOperation(value = "工时提交记录显示", httpMethod = "GET")
    @Log(desc = "当前用户工时提交信息记录")
    @GetMapping("mySubWorkTimeInfoList")
    @ResponseBody
    public ReType mySubWorkTimeInfoList(String proName, String infoStatus, String page, String limit) {
        // 排除当前用户参与的未完成导入人员的项目
        String id= Principal.getPrincipal().getId();
        WorkTimeInfoDetail workTimeInfoDetail = new WorkTimeInfoDetail();
        workTimeInfoDetail.setSendUserId(id);
        workTimeInfoDetail.setProName(proName);
        workTimeInfoDetail.setInfoStatus(infoStatus);
        List<WorkTimeInfoDetail> workTimeInfoDetails = workTimeInfoDetailService.selectBySendUserId(workTimeInfoDetail);
        for(int j=0; j<workTimeInfoDetails.size(); j++){
            sendDealStatus(workTimeInfoDetails.get(j));
        }
        return new ReType(workTimeInfoDetails.size(), workTimeInfoDetails);
    }

    @GetMapping("/check")
    public String check(Model model) {
        return "workTime/myDealList";
    }

    @ApiOperation(value = "工时需要处理的记录显示", httpMethod = "GET")
    @Log(desc = "当前用户工时需要处理的工时信息记录")
    @GetMapping("myDealWorkTimeInfoList")
    @ResponseBody
    public ReType myDealWorkTimeInfoList(String proName, String infoStatus, String page, String limit) {
        String id= Principal.getPrincipal().getId();
        WorkTimeInfoDetail workTimeInfoDetail = new WorkTimeInfoDetail();
        workTimeInfoDetail.setReceiveUserId(id);
        workTimeInfoDetail.setProName(proName);
        workTimeInfoDetail.setInfoStatus(infoStatus);
        List<WorkTimeInfoDetail> workTimeInfoDetails = workTimeInfoDetailService.selectByReceiveUserId(workTimeInfoDetail);
        for(int j=0; j<workTimeInfoDetails.size(); j++){
            String activName = workTimeInfoDetails.get(j).getActivId();
            char firstChioce = activName.charAt(2);
            char secondChoice = activName.charAt(5);
            workTimeInfoDetails.get(j).setActivId(trans(firstChioce, secondChoice));
            receiveDealStatus(workTimeInfoDetails.get(j));
        }
        return new ReType(workTimeInfoDetails.size(), workTimeInfoDetails);
    }

    @ApiOperation(value = "确认通过提交的工时信息", httpMethod = "POST")
    @Log(desc = "确认通过提交的工时信息")
    @PostMapping("acceptWorkTimeInfo")
    @ResponseBody
    public JsonUtil acceptWorkTimeInfo(String id){
        JsonUtil j = new JsonUtil();
        WorkTimeInfoDetail workTimeInfoDetail = new WorkTimeInfoDetail();
        workTimeInfoDetail.setId(id);
        workTimeInfoDetail.setInfoStatus("accepted");
        String msg="已确认";
        try{
            workTimeInfoDetailService.updateInfoStatus(workTimeInfoDetail);
        }catch (MyException e) {
            msg = "确认失败";
            j.setFlag(false);
            j.setMsg(msg);
            e.printStackTrace();
        }
        j.setFlag(true);
        j.setMsg(msg);
        return j;
    }

    @ApiOperation(value = "驳回通过提交的工时信息", httpMethod = "POST")
    @Log(desc = "确认通过提交的工时信息")
    @PostMapping("refuseWorkTimeInfo")
    @ResponseBody
    public JsonUtil refuseWorkTimeInfo(String id){
        JsonUtil j = new JsonUtil();
        WorkTimeInfoDetail workTimeInfoDetail = new WorkTimeInfoDetail();
        workTimeInfoDetail.setId(id);
        workTimeInfoDetail.setInfoStatus("refused");
        String msg="已驳回";
        try{
            workTimeInfoDetailService.updateInfoStatus(workTimeInfoDetail);
        }catch (MyException e) {
            msg = "驳回失败";
            j.setFlag(false);
            j.setMsg(msg);
            e.printStackTrace();
        }
        j.setFlag(true);
        j.setMsg(msg);
        return j;
    }

    @GetMapping("/showMySubWorkInfo")
    public String showMySubWorkInfo(Model model, String id) {
        WorkTimeInfoDetail workTimeInfoDetail = workTimeInfoDetailService.selectById(id);
        String activName = workTimeInfoDetail.getActivId();
        char firstChioce = activName.charAt(2);
        char secondChoice = activName.charAt(5);
        workTimeInfoDetail.setActivId(trans(firstChioce, secondChoice));
        System.out.println(workTimeInfoDetail);
        model.addAttribute("baseInfo",workTimeInfoDetail);
        return "workTime/subInfoDetail";
    }

    @GetMapping("/showMyDealWorkInfo")
    public String showMyDealWorkInfo(Model model, String id) {
        WorkTimeInfoDetail workTimeInfoDetail = workTimeInfoDetailService.selectById(id);
        String activName = workTimeInfoDetail.getActivId();
        char firstChioce = activName.charAt(2);
        char secondChoice = activName.charAt(5);
        workTimeInfoDetail.setActivId(trans(firstChioce, secondChoice));
        System.out.println(workTimeInfoDetail);
        model.addAttribute("baseInfo",workTimeInfoDetail);
        return "workTime/dealInfoDetail";
    }

    @GetMapping("/updateMySubWorkInfo")
    public String updateMySubWorkInfo(Model model, String id) {
        WorkTimeInfoDetail workTimeInfoDetail = workTimeInfoDetailService.selectById(id);
        String activName = workTimeInfoDetail.getActivId();
        char firstChioce = activName.charAt(2);
        char secondChoice = activName.charAt(5);
        workTimeInfoDetail.setActivId(trans(firstChioce, secondChoice));
        System.out.println(workTimeInfoDetail);
        model.addAttribute("baseInfo",workTimeInfoDetail);
        return "workTime/updateMySub";
    }

    @ApiOperation(value = "修改提交的工时信息", httpMethod = "POST")
    @Log(desc = "修改提交的工时信息")
    @PostMapping("updateMySubmWorInfo")
    @ResponseBody
    public JsonUtil updateMySubmWorInfo(String id, String receiveUserId, String proId, String activId, String funcId,
                                        String funcName, String startTime, String endTime,
                                        String submitDate, String note) {
        JsonUtil j = new JsonUtil();
        String msg = "工时信息修改成功";
        WorkTimeInfoDetail worIn = new WorkTimeInfoDetail();
        worIn.setProId(proId);
        worIn.setReceiveUserId(receiveUserId);
        worIn.setSubmitDate(submitDate);
        worIn.setActivId(activId);
        worIn.setId(id);
        worIn.setFuncId(funcId);
        worIn.setFuncName(funcName);
        worIn.setStartTime(startTime);
        worIn.setEndTime(endTime);
        worIn.setNote(note);
        worIn.setInfoStatus("processing");
        int renum = workTimeInfoDetailService.selectHasSubmitted(worIn);
        if(renum == 1){
            WorkTimeInfoDetail workTimeInfoDetail = workTimeInfoDetailService.selectHasSub(worIn);
            if(workTimeInfoDetail.getId().equals(id)){
                try{
                    workTimeInfoDetailService.updateById(worIn);
                }catch (MyException e) {
                    msg = "修改失败";
                    j.setFlag(false);
                    j.setMsg(msg);
                    e.printStackTrace();
                    return j;
                }
            }else{
                msg = "修改失败,重复提交工时";
                j.setFlag(false);
                j.setMsg(msg);
                return j;
            }
        }
        else{
            try{
                workTimeInfoDetailService.updateById(worIn);
            }catch (MyException e) {
                msg = "修改失败";
                j.setFlag(false);
                j.setMsg(msg);
                e.printStackTrace();
                return j;
            }
        }
        j.setFlag(true);
        j.setMsg(msg);
        return j;

    }


}
