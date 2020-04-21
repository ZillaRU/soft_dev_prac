package com.len.controller;


import com.alibaba.fastjson.JSON;
import com.len.core.annotation.Log;
import com.len.core.shiro.Principal;
import com.len.entity.*;
import com.len.enume.RiskTypeEnum;
import com.len.exception.MyException;
import com.len.qo.RskDetail;
import com.len.service.*;
import com.len.service.impl.MailService;
import com.len.util.JsonUtil;
import com.len.util.ReType;
import com.len.vo.RskDetailInfo;
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
@RequestMapping(value = "/risk")
public class RiskInfoController {
    @Autowired
    private RiskInfoService riskInfoService;
    @Autowired
    private SysUserService userService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private RskReUsrService rskReUsrService;
    @Autowired
    private MailService mailService;
    @Autowired
    private ProWorInfoManService proWorInfoManService;

    @GetMapping("addRiskInfo")
    public String addRiskInfo(Model model) {
        String id = Principal.getPrincipal().getId();
        SysUser user = userService.selectByPrimaryKey(id);
        model.addAttribute("user", user);
        return "rsk/add-risk-info";
    }

    @ApiOperation(value = "新增风险", httpMethod = "POST")
    @Log(desc = "新增项目风险")
    @PostMapping("addRisk")
    @ResponseBody
    public JsonUtil addRiskInfo(RskDetailInfo rskDetailInfo) {
        JsonUtil j = new JsonUtil();
        String msg = "新增项目风险信息成功";
        try {
            String rId = UUID.randomUUID().toString();
            RskReUsr rskReUsr = new RskReUsr();
            String[] l = rskDetailInfo.getHMember();
            for (String ll : l) {
                System.out.println(ll);
                rskReUsr.sethId(rId);
                rskReUsr.setuId(ll);
                rskReUsr.setuName(userService.selectByPrimaryKey(ll).getRealName());
                rskReUsrService.insertSelective(rskReUsr);
            }

            RskInfo rskInfo = new RskInfo(rId, rskDetailInfo.getPId(),
                    rskDetailInfo.getHType(), rskDetailInfo.getHDes(), rskDetailInfo.getHGrade(),
                    rskDetailInfo.getHInfluence(), rskDetailInfo.getHTactics(), rskDetailInfo.getHState(),
                    0, rskDetailInfo.getHManager(), Principal.getPrincipal().getId());
            System.out.println(rskInfo.gethId() + ' ' + rskInfo.gethInfluence() + ' '
                    + rskInfo.gethDes() + ' ' + rskInfo.gethGrade() + ' ' + rskInfo.gethState()
                    + rskInfo.gethFrequency() + ' ' + rskInfo.gethTactics() + ' ' + rskInfo.gethId() + ' '
                    + rskInfo.gethManager() + ' '
            );

            riskInfoService.insertSelective(rskInfo);

        } catch (MyException e) {
            msg = "保存失败";
            j.setFlag(false);
            e.printStackTrace();
        }
        j.setMsg(msg);
        return j;
    }

    @GetMapping("showList")
    public String showRiskList(Model model) {
        String id = Principal.getPrincipal().getId();
        SysUser user = userService.selectByPrimaryKey(id);
        model.addAttribute("user", user);
        return "rsk/rsk-list-show";
    }


    @ApiOperation(value = "风险列表", httpMethod = "GET")
    @Log(desc = "风险列表")
    @GetMapping("showRiskList")
    @ResponseBody
    public ReType showRskList(Model model) {
        System.out.println("showRsklist!");
        List<RskDetail> list = new ArrayList<>();
        List<ProWorInfoMan> proWorInfoManList = proWorInfoManService.selectByUId(Principal.getPrincipal().getId());
        for (ProWorInfoMan p : proWorInfoManList) {
            List<RskInfo> rskInfoList = riskInfoService.selectByPmId(p.getProId());
            for (RskInfo i : rskInfoList) {
                System.out.println("aaaa:" + i.gethId());
                List<RskReUsr> rskReUsrList = rskReUsrService.selectByRId(i.gethId());
                String[] members = new String[rskReUsrList.size()];
                int k = 0;
                for (RskReUsr rskReUsr : rskReUsrList) {
                    members[k] = rskReUsr.getuName();
                    k++;
                }
                String type = RiskTypeEnum.getValue(Integer.parseInt(i.gethType())).getMessage();
                RskDetail rskDetail = new RskDetail(i.gethId(), p.getProId(), p.getProName(), type,
                        i.gethDes(), i.gethGrade(), i.gethInfluence(), i.gethTactics(), i.gethState(),
                        i.gethFrequency(), " ", members, i.gethCreator());

                list.add(rskDetail);
            }
        }

        return new ReType(list.size(), list);
    }

    @GetMapping("showRiskDetail")
    public String showProjDetail(Model model, String riskId) {
        RskInfo i = riskInfoService.selectByPrimaryKey(riskId);
        ProjectInfo projectInfo = projectInfoService.selectByPrimaryKey(i.getpId());
        List<RskReUsr> rskReUsrList = rskReUsrService.selectByRId(riskId);
        String[] members = new String[rskReUsrList.size()];
        int k = 0;
        for (RskReUsr rskReUsr : rskReUsrList) {
            members[k] = rskReUsr.getuName();
            k++;
        }
        SysUser sysUser = userService.selectByPrimaryKey(i.gethManager());
        String type = RiskTypeEnum.getValue(Integer.parseInt(i.gethType())).getMessage();
        RskDetail rskDetail = new RskDetail(i.gethId(), i.getpId(), projectInfo.getProjName(), type,
                i.gethDes(), i.gethGrade(), i.gethInfluence(), i.gethTactics(), i.gethState(),
                i.gethFrequency(), sysUser.getRealName(), members, i.gethCreator());

        System.out.println("addRisk");
        System.out.println(JSON.toJSONString(rskDetail));
        model.addAttribute("riskDetail", rskDetail);
        return "rsk/risk-detail";
    }

    @GetMapping("importRisk")
    public String importRisk() {
        return "rsk/import-risk";
    }

    @GetMapping("importSimilarRisk")
    public String importSimilarRisk(Model model, String proId) {
        //随便封装一个对象哈哈哈哈哈哈
        ProWorInfoMan proWorInfoMan = new ProWorInfoMan();
        proWorInfoMan.setProId(proId);
        model.addAttribute("proWorInfoMan", proWorInfoMan);
        return "rsk/import-similar-risk";
    }

    @ApiOperation(value = "导入相似风险", httpMethod = "GET")
    @Log(desc = "导入相似风险")
    @GetMapping("importSimilarProRisk")
    @ResponseBody
    public ReType importSimilarProRisk(String proId) {
        System.out.println("importSimilarProRisk : " + proId);
        ProjectInfo project = projectInfoService.selectByPrimaryKey(proId);
        List<ProjectInfo> projectInfoList = projectInfoService.selectByPmId(project.getPmId());
        List<RskInfo> list = new ArrayList<>();
        for (ProjectInfo projectInfo : projectInfoList) {
            if (projectInfo.getProjState().equals("进行中")) {
                List<RskInfo> rskInfoList = riskInfoService.selectByPmId(projectInfo.getId());
                list.addAll(rskInfoList);
            }
        }
        System.out.println("SimilarRisk");
        System.out.println(JSON.toJSONString(list));
        return new ReType(list.size(), list);
    }


    @ApiOperation(value = "删除风险", httpMethod = "GET")
    @Log(desc = "删除风险")
    @GetMapping("deleteRisk")
    @ResponseBody
    public String deleteRisk(String riskId) {
        List<RskReUsr> rskReUsrList = rskReUsrService.selectByRId(riskId);
        for (RskReUsr rskReUsr : rskReUsrList)
            rskReUsrService.delete(rskReUsr);
        riskInfoService.deleteByPrimaryKey(riskId);
        return "success";
    }

    @GetMapping("editRisk")
    public String editRiskInfo(Model model, String riskId) {
        RskInfo i = riskInfoService.selectByPrimaryKey(riskId);
        ProjectInfo projectInfo = projectInfoService.selectByPrimaryKey(i.getpId());
        List<RskReUsr> rskReUsrList = rskReUsrService.selectByRId(riskId);

        SysUser sysUser = userService.selectByPrimaryKey(i.gethManager());
        String type = RiskTypeEnum.getValue(Integer.parseInt(i.gethType())).getMessage();

        String[] members = new String[rskReUsrList.size()];
        int k = 0;
        for (RskReUsr rskReUsr : rskReUsrList) {
            members[k] = rskReUsr.getuId();
            k++;
        }
        RskDetail rskDetail = new RskDetail(i.gethId(), i.getpId(), projectInfo.getProjName(), type,
                i.gethDes(), i.gethGrade(), i.gethInfluence(), i.gethTactics(), i.gethState(),
                i.gethFrequency(), sysUser.getRealName(), members, i.gethCreator());

        System.out.println("editRisk");
        System.out.println(JSON.toJSONString(rskDetail));
        model.addAttribute("riskDetail", rskDetail);
        return "rsk/edit-risk-info";
    }


    @ApiOperation(value = "更新风险", httpMethod = "POST")
    @Log(desc = "更新项目风险")
    @PostMapping("updateRisk")
    @ResponseBody
    public JsonUtil updateRiskInfo(RskDetailInfo rskDetailInfo) {
        JsonUtil j = new JsonUtil();
        String msg = "更新项目风险信息成功";
        try {
            System.out.println("updateRisk");
            System.out.println(JSON.toJSONString(rskDetailInfo));


            RskInfo rskInfo = riskInfoService.selectByPrimaryKey(rskDetailInfo.getHId());
            rskInfo.sethType(rskDetailInfo.getHType());
            rskInfo.sethDes(rskDetailInfo.getHDes());
            rskInfo.sethManager(rskDetailInfo.getHManager());
            rskInfo.sethInfluence(rskDetailInfo.getHInfluence());
            rskInfo.sethGrade(rskDetailInfo.getHGrade());
            rskInfo.sethTactics(rskDetailInfo.getHTactics());
            rskInfo.sethState(rskDetailInfo.getHState());

            riskInfoService.updateByPrimaryKey(rskInfo);

            rskReUsrService.deleteByRId(rskDetailInfo.getHId());

            List<RskReUsr> rskReUsrList = new ArrayList<>();
            String[] members = rskDetailInfo.getHMember();
            for (int i = 0; i < members.length; i++) {
                RskReUsr rskReUsr = new RskReUsr();
                rskReUsr.sethId(rskDetailInfo.getHId());
                rskReUsr.setuId(members[i]);
                SysUser sysUser = userService.selectByPrimaryKey(members[i]);
                rskReUsr.setuName(sysUser.getRealName());
                System.out.println("update rskreuser");
                System.out.println(JSON.toJSONString(rskReUsr));
                rskReUsrList.add(rskReUsr);
            }

            rskReUsrService.insertList(rskReUsrList);


        } catch (MyException e) {
            msg = "保存失败";
            j.setFlag(false);
            e.printStackTrace();
        }
        j.setMsg(msg);
        return j;
    }


    @ApiOperation(value = "跟踪风险", httpMethod = "GET")
    @Log(desc = "跟踪风险")
    @GetMapping("traceRisk")
    @ResponseBody
    public String RiskTracing(String riskId) {
        RskInfo rskInfo = riskInfoService.selectByPrimaryKey(riskId);
        System.out.println(rskInfo.gethFrequency());
        rskInfo.sethFrequency(1 + rskInfo.gethFrequency());
        System.out.println(rskInfo.gethFrequency());
        riskInfoService.updateByPrimaryKey(rskInfo);
        return "success";
    }

    @ApiOperation(value = "发邮件", httpMethod = "GET")
    @Log(desc = "发邮件提醒跟踪")
    @GetMapping("emailRisk")
    @ResponseBody
    public String RiskEmail(String riskId) {
        List<RskReUsr> rskReUsrList = rskReUsrService.selectByRId(riskId);
        for (RskReUsr rskReUsr : rskReUsrList) {
            SysUser sysUser = userService.selectByPrimaryKey(rskReUsr.getuId());
            mailService.sendMail("风险跟踪提醒",
                    "您编号为" + riskId + "的一条风险信息，风险跟踪频度小于等于1次，请您尽快跟踪风险(～￣▽￣)～",
                    sysUser.getEmail()
            );
        }

        return "success";
    }

}

