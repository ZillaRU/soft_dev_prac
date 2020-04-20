package com.len.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.len.core.annotation.Log;
import com.len.core.shiro.Principal;
import com.len.entity.*;
import com.len.exception.MyException;
import com.len.service.ProWorInfoManService;
import com.len.service.ProjectInfoService;
import com.len.service.ProjectWorkerInfoService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/projectWorker")

public class ProjectWorkerInfoController {

    @Autowired
    private ProjectWorkerInfoService projectWorkerInfoService;

    @Autowired
    private ProjectInfoService projectInfoService;

    @Autowired
    private ProWorInfoManService proWorInfoManService;

    @Autowired
    private SysUserService userService;

    public static final List<String> listr = new ArrayList<String>() {
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

    public String definFinish(String proId) {
        ProWorInfoMan proWorInfoMan = new ProWorInfoMan();
        proWorInfoMan.setProId(proId);
        String proStatus = "notFinish";
        boolean flag = true;
        for (int i = 0; i < listr.size(); i++) {
            proWorInfoMan.setProRoleName(listr.get(i));
            int resnum = proWorInfoManService.selectRoleNum(proWorInfoMan);
            if (resnum == 0) {
                proStatus = "notFinish";
                flag = false;
            }
        }
        if (flag) {
            proStatus = "finished";
        }
        return proStatus;
    }

    @GetMapping("showInfo")
    public String showInfo(Model model) {
        String id = Principal.getPrincipal().getId();
        SysUser user = userService.selectByPrimaryKey(id);
        System.out.println(JSON.toJSONString(user));
        model.addAttribute("user", user);
        return "act/projectWorker/showWorkerInfo";
    }

    @GetMapping("worInfoMan")
    public String worGroupMan() {
        return "act/projectWorker/worInfoMan";
    }

    @GetMapping("showProDetail")
    public String showDetail(Model model, String proId) {
        ProjectWorkerInfo projectWorkerInfo = projectWorkerInfoService.selectByPrimaryKey(proId);
        if (projectWorkerInfo.getProStatus().equals("notFinish")) {
            projectWorkerInfo.setProStatus("未完成");
        } else {
            projectWorkerInfo.setProStatus("已完成");
        }
        model.addAttribute("baseInfo", projectWorkerInfo);
        return "act/projectWorker/showProDetail";
    }

    public ProWorInfoTmp findProUsername(ProjectWorkerInfo worInf) {
        String proId;
        String resU = "";
        ProWorInfoMan pwim = new ProWorInfoMan();
        ProWorInfoTmp proWorInfoTmp = new ProWorInfoTmp();
        List<ProWorInfoMan> resUser;
        proId = worInf.getProId();
        proWorInfoTmp.setProId(proId);
        pwim.setProId(proId);
        String proNa = worInf.getProName();
        proWorInfoTmp.setProName(proNa);
        for (int ii = 0; ii < listr.size(); ii++) {
            resU = "";
            pwim.setProRoleName(listr.get(ii));
            resUser = proWorInfoManService.selectUserByRoleName(pwim);
            if (resUser.size() > 0) {
                for (int jj = 0; jj < resUser.size() - 1; jj++) {
                    resU += resUser.get(jj).getUserName();
                    resU += ",";
                }
                resU += resUser.get(resUser.size() - 1).getUserName();
            }
            if (listr.get(ii).equals("dev")) {
                proWorInfoTmp.setDev(resU);
            } else if (listr.get(ii).equals("devleader")) {
                proWorInfoTmp.setDevLeader(resU);
            } else if (listr.get(ii).equals("test")) {
                proWorInfoTmp.setTest(resU);
            } else if (listr.get(ii).equals("testleader")) {
                proWorInfoTmp.setTestLeader(resU);
            } else if (listr.get(ii).equals("confman")) {
                proWorInfoTmp.setConfMan(resU);
            } else if (listr.get(ii).equals("qa")) {
                proWorInfoTmp.setQa(resU);
            } else {
                proWorInfoTmp.setEpg(resU);
            }
        }
        return proWorInfoTmp;
    }

    public ProWorInfoMan transMi(ProWorInfoMan worInf) {

        if (worInf.getProRoleName().equals("dev")) {
            worInf.setProRoleName("开发人员");
        } else if (worInf.getProRoleName().equals("devleader")) {
            worInf.setProRoleName("开发负责人");
        } else if (worInf.getProRoleName().equals("test")) {
            worInf.setProRoleName("测试人员");
        } else if (worInf.getProRoleName().equals("testleader")) {
            worInf.setProRoleName("测试负责人");
        } else if (worInf.getProRoleName().equals("confman")) {
            worInf.setProRoleName("配置管理人员");
        } else if (worInf.getProRoleName().equals("qa")) {
            worInf.setProRoleName("QA");
        } else {
            worInf.setProRoleName("EPG");
        }
        return worInf;
    }

    @ApiOperation(value = "项目人员信息", httpMethod = "GET")
    @Log(desc = "当前项目经理管理的项目人员信息")
    @GetMapping("info")
    @ResponseBody
    public ReType proWorkerInfo(Model mol, String proName, ProjectWorkerInfo worInfo, String page, String limit) {
        String id = Principal.getPrincipal().getId();
        worInfo.setPmId(id);
        worInfo.setProName(proName);
        List<ProWorInfoTmp> res = new ArrayList<>();
        List<ProjectWorkerInfo> worInf = projectWorkerInfoService.selectByProName(worInfo);
        for (int i = 0; i < worInf.size(); i++) {
            ProWorInfoTmp proWorInfoTmp = findProUsername(worInf.get(i));
            if (worInf.get(i).getProStatus().equals("notFinish")) {
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
        if (worInf.size() >= 1) {
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
        String id = Principal.getPrincipal().getId();
        proWorInfoMan.setPmId(id);
        proWorInfoMan.setProName(proName);
        List<ProWorInfoMan> worInfo = proWorInfoManService.selectByProName(proWorInfoMan);
        if (worInfo.size() >= 1) {
            for (int i = 0; i < worInfo.size(); i++) {
                if (worInfo.get(i).getProRoleName().equals("dev")) {
                    worInfo.get(i).setProRoleName("开发人员");
                } else if (worInfo.get(i).getProRoleName().equals("devleader")) {
                    worInfo.get(i).setProRoleName("开发负责人");
                } else if (worInfo.get(i).getProRoleName().equals("test")) {
                    worInfo.get(i).setProRoleName("测试人员");
                } else if (worInfo.get(i).getProRoleName().equals("testleader")) {
                    worInfo.get(i).setProRoleName("测试负责人");
                } else if (worInfo.get(i).getProRoleName().equals("confman")) {
                    worInfo.get(i).setProRoleName("配置管理人员");
                } else if (worInfo.get(i).getProRoleName().equals("qa")) {
                    worInfo.get(i).setProRoleName("QA");
                } else {
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
    public JsonUtil delWor(String id) {
        JsonUtil j = new JsonUtil();
        String proStatus;
        String msg = "删除成功";
        ProWorInfoMan man = new ProWorInfoMan();
        ProjectWorkerInfo projectWorkerInfo = new ProjectWorkerInfo();
        man.setId(id);
        String proId = proWorInfoManService.selectByPrimaryKey(man).getProId();
        projectWorkerInfo.setProId(proId);
        try {
            proWorInfoManService.delById(id);
        } catch (MyException e) {
            msg = "删除失败";
            j.setFlag(false);
            j.setMsg(msg);
            e.printStackTrace();
        }
        j.setFlag(true);
        j.setMsg(msg);
        proStatus = definFinish(proId);
        projectWorkerInfo.setProStatus(proStatus);
        projectWorkerInfoService.updateProStatus(projectWorkerInfo);
        return j;
    }

    @GetMapping(value = "showAddProWor")
    public String goAddProWor() {
        return "act/projectWorker/addProWorInfo";
    }

    @GetMapping(value = "showUpdProWor")
    public String goUpdProWor(Model model, String id) {
        ProWorInfoMan proWorInfoMan = proWorInfoManService.selectByPrimaryKey(id);
        System.out.println(proWorInfoMan);
        model.addAttribute("currentInfo", proWorInfoMan);
        transMi(proWorInfoMan);
        model.addAttribute("currentInf", proWorInfoMan);
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
    public JsonUtil addProWor(String proId, String proName, String userId, String proRoleName) {
        JsonUtil j = new JsonUtil();
        String msg = "新增项目人员成功";
        ProjectWorkerInfo workerInfo = new ProjectWorkerInfo();
        workerInfo.setProId(proId);
        if (proRoleName.equals("devleader") || proRoleName.equals("testleader") || proRoleName.equals("qa") || proRoleName.equals("confman") || proRoleName.equals("epg")) {
            ProWorInfoMan proWor = new ProWorInfoMan();
            proWor.setProId(proId);
            proWor.setProRoleName(proRoleName);
            proWor.setUserId(userId);
            int resnum = proWorInfoManService.selectRoleNum(proWor);
            if (resnum == 1) {
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
        try {
            proWorInfoManService.insertProWor(proWorInfoMan);
        } catch (MyException e) {
            msg = "新增项目人员失败";
            j.setFlag(false);
            j.setMsg(msg);
            e.printStackTrace();
        }
        j.setFlag(true);
        j.setMsg(msg);
        proStatus = definFinish(proId);
        workerInfo.setProStatus(proStatus);
        projectWorkerInfoService.updateProStatus(workerInfo);
        return j;
    }

    @ApiOperation(value = "修改项目人员信息", httpMethod = "POST")
    @Log(desc = "修改项目人员信息")
    @PostMapping("updProWor")
    @ResponseBody
    public JsonUtil updProWor(String id, String userId, String proId, String proRoleName) {
        JsonUtil j = new JsonUtil();
        String msg = "项目人员信息修改成功";
        ProWorInfoMan proWorInfoMan = new ProWorInfoMan();
        ProjectWorkerInfo projectWorkerInfo = new ProjectWorkerInfo();
        projectWorkerInfo.setProId(proId);
        proWorInfoMan.setProId(proId);
        proWorInfoMan.setProRoleName(proRoleName);
        proWorInfoMan.setUserId(userId);
        int num = proWorInfoManService.selectSameCondi(proWorInfoMan);
        System.out.println(num);
        if (num >= 1) {
            msg = "修改失败,该用户已在此项目承担了此角色";
            j.setFlag(false);
            j.setMsg(msg);
            return j;
        }
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
                proStatus = definFinish(proId);
                projectWorkerInfo.setProStatus(proStatus);
                projectWorkerInfoService.updateProStatus(projectWorkerInfo);
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
            proStatus = definFinish(proId);
            projectWorkerInfo.setProStatus(proStatus);
            projectWorkerInfoService.updateProStatus(projectWorkerInfo);

            return j;
        }
    }
}


