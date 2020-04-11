package com.len.controller;


import com.alibaba.fastjson.JSON;
import com.len.core.annotation.Log;
import com.len.core.shiro.Principal;
import com.len.entity.ProjectInfo;
import com.len.entity.RskInfo;
import com.len.entity.SysUser;
import com.len.exception.MyException;
import com.len.qo.RskDetail;
import com.len.service.ProjectInfoService;
import com.len.service.RiskInfoService;
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
@RequestMapping(value = "/risk")
public class RiskInfoController {
    @Autowired
    private RiskInfoService riskInfoService;
    @Autowired
    private SysUserService userService;
    @Autowired
    private ProjectInfoService projectInfoService;

    @GetMapping("addRiskInfo")
    public String addRiskInfo(Model model) {
        String id = Principal.getPrincipal().getId();
        SysUser user = userService.selectByPrimaryKey(id);
        model.addAttribute("user", user);
        return "rsk/add-risk-info";
    }

    @ApiOperation(value = "新增风险",httpMethod = "POST")
    @Log(desc = "新增项目风险")
    @PostMapping("addRisk")
    @ResponseBody
    public JsonUtil addRiskInfo(RskInfo rskInfo){
        JsonUtil j = new JsonUtil();
        String msg = "新增项目风险信息成功";
        try {
//            System.out.println(rskInfo.gethId()+' '+rskInfo.gethInfluence()+' '
//                    +rskInfo.gethDes()+' '+rskInfo.gethGrade()+' '+rskInfo.gethState()
//                    +rskInfo.gethFrequency()+' '+rskInfo.gethTactics()+' '+rskInfo.getpId()
//            );
            rskInfo.sethId(UUID.randomUUID().toString());
//            rskInfo.sethManager(Principal.getPrincipal().getId());
//            rskInfo.sethRelate("a");
            rskInfo.sethFrequency(0);
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


    @ApiOperation(value = "风险列表",httpMethod = "GET")
    @Log(desc = "风险列表")
    @GetMapping("showRiskList")
    @ResponseBody
    public ReType showRskList(Model model) {
        System.out.println("showRsklist!!!!!!!!!!!!!:"+Principal.getPrincipal().getId());
        List<RskDetail> list=new ArrayList<>();
        List<ProjectInfo> proList = projectInfoService.selectByPmId(Principal.getPrincipal().getId());
        for(ProjectInfo p:proList){
            List<RskInfo> rskInfoList = riskInfoService.selectByPmId(p.getId());
            for(RskInfo i:rskInfoList){
                System.out.println("aaaa:"+i.gethId());
                RskDetail rskDetail=new RskDetail();
                rskDetail.setHId(i.gethId());
                rskDetail.setPId(p.getId());
                rskDetail.setPName(p.getProjName());
                System.out.println(p.getProjName());
                rskDetail.setHDes(i.gethDes());
                rskDetail.setHFrequency(i.gethFrequency());
                rskDetail.setHGrade(i.gethGrade());
                rskDetail.setHInfluence(i.gethInfluence());
                rskDetail.setHState(i.gethState());
                rskDetail.setPManager(i.gethManager());
                rskDetail.setHTactics(i.gethTactics());
                rskDetail.setHType(i.gethType());
//                rskDetail.setPMember(member);

                list.add(rskDetail);
            }
        }

        return new ReType(list.size(), list);
    }

    @GetMapping("showRiskDetail")
    public String showProjDetail(Model model, String riskId) {
        RskInfo i=riskInfoService.selectByPrimaryKey(riskId);
        ProjectInfo projectInfo=projectInfoService.selectByPrimaryKey(i.getpId());
        //todo-addprojectMemberRelationService
//        List<String> member=new ArrayList<>();
//        member.add("memberA");
//        member.add("memberB");
        RskDetail rskDetail=new RskDetail();
        rskDetail.setHId(i.gethId());
        rskDetail.setPId(i.getpId());
        rskDetail.setPName(projectInfo.getPmName());
        rskDetail.setHDes(i.gethDes());
        rskDetail.setHFrequency(i.gethFrequency());
        rskDetail.setHGrade(i.gethGrade());
        rskDetail.setHInfluence(i.gethInfluence());
        rskDetail.setHState(i.gethState());
        rskDetail.setHTactics(i.gethTactics());
        rskDetail.setHType(i.gethType());
//        rskDetail.setPMember(member);
        rskDetail.setPManager(i.gethManager());
        System.out.println(JSON.toJSONString(rskDetail));
        model.addAttribute("riskDetail", rskDetail);
        return "rsk/risk-detail";
    }
}

