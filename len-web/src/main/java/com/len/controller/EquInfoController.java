package com.len.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.len.core.annotation.Log;
import com.len.entity.EquInfo;
import com.len.entity.ProWorInfoMan;
import com.len.entity.ProjectInfo;
import com.len.exception.MyException;
import com.len.qo.EquDetail;
import com.len.service.EquInfoService;
import com.len.service.ProWorInfoManService;
import com.len.service.ProjectInfoService;
import com.len.util.JsonUtil;
import com.len.util.ReType;
import com.len.vo.EquDetailInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/equ")
public class EquInfoController {
    @Autowired
    private EquInfoService equInfoService;

    @Autowired
    private ProjectInfoService projectInfoService;

    @Autowired
    private ProWorInfoManService proWorInfoManService;

    @GetMapping(value = "showList")
    public String showEqu() {
        return "equ/equList";
    }

    @ApiOperation(value = "/showEquList", httpMethod = "GET", notes = "设备列表")
    @GetMapping(value = "showEquList")
    @ResponseBody
    public ReType showEquList(EquInfo equInfo, Model model, String page, String limit) {
        System.out.println("aaaa:" + equInfo.getrId() + equInfo.getpName() + equInfo.getrManager() + equInfo.getrDate() + equInfo.getrState()
                + equInfo.getRentState() + equInfo.getrFinish());
        return equInfoService.show(equInfo, Integer.valueOf(page), Integer.valueOf(limit));
    }

    @GetMapping("showEquDetail")
    public String showEquDetail(Model model, String rId) {
        EquInfo i = equInfoService.selectByPrimaryKey(rId);
        EquDetail equDetail = new EquDetail(i.getrId(), i.getpName(),
                i.getrManager(), i.getrDate(), i.getrState(), i.getRentState(), i.getrFinish());
        model.addAttribute("equDetail", equDetail);
        return "equ/equ-detail";
    }

    @ApiOperation(value = "/showAllEquList", httpMethod = "GET", notes = "设备列表")
    @GetMapping(value = "showAllEquList")
    @ResponseBody
    public String showEquList(EquInfo equInfo) {
        return equInfoService.showAll(equInfo);
    }

    @ApiOperation(value = "删除设备", httpMethod = "GET")
    @Log(desc = "删除设备")
    @GetMapping("deleteEqu")
    @ResponseBody
    public String deleteEqu(String rId) {
        equInfoService.deleteByPrimaryKey(rId);
        return "success";
    }

    @GetMapping("addEquInfo")
    public String addEquInfo() {
        return "equ/add-equ";
    }

    @ApiOperation(value = "新增设备", httpMethod = "POST")
    @Log(desc = "添加设备")
    @PostMapping("addEqu")
    @ResponseBody
    public JsonUtil addEquInfo(EquDetailInfo equDetailInfo) {
        JsonUtil j = new JsonUtil();
        String msg = "新增项目风险信息成功";
        try {
            EquInfo equInfo = new EquInfo(equDetailInfo.getRId(),
                    equDetailInfo.getPName(), equDetailInfo.getRManager(), equDetailInfo.getRDate(),
                    equDetailInfo.getRState(), equDetailInfo.getRentState(), equDetailInfo.getRFinish());
            System.out.println(equInfo);
            equInfoService.insertSelective(equInfo);
        } catch (MyException e) {
            msg = "保存失败";
            j.setFlag(false);
            e.printStackTrace();
        }
        j.setMsg(msg);
        return j;

    }

    @GetMapping("editEqu")
    public String editEquInfo(Model model, String rId) {
        EquInfo i = equInfoService.selectByPrimaryKey(rId);
        EquDetail equDetail = new EquDetail(i.getrId(), i.getpName(),
                i.getrManager(), i.getrDate(), i.getrState(), i.getRentState(), i.getrFinish());
        System.out.println("editEqu");
        System.out.println(JSON.toJSONString(equDetail));
        model.addAttribute("equDetail", equDetail);
        return "equ/edit-equ";
    }


    @ApiOperation(value = "更新设备", httpMethod = "POST")
    @Log(desc = "更新设备信息")
    @PostMapping("updateEqu")
    @ResponseBody
    public JsonUtil updateEquInfo(EquDetailInfo equDetailInfo) {
        JsonUtil j = new JsonUtil();
        String msg = "更新设备信息成功";
        try {
            System.out.println("updateEqu");
            System.out.println(JSON.toJSONString(equDetailInfo));
            EquInfo equInfo = equInfoService.selectByPrimaryKey(equDetailInfo.getRId());
            equInfo.setrDate(equDetailInfo.getRDate());
            equInfo.setrState(equDetailInfo.getRState());
            equInfo.setRentState(equDetailInfo.getRentState());
            equInfo.setrFinish(equDetailInfo.getRFinish());
            equInfoService.updateByPrimaryKey(equInfo);
        } catch (MyException e) {
            msg = "保存失败";
            j.setFlag(false);
            e.printStackTrace();
        }
        j.setMsg(msg);
        return j;
    }

    @PostMapping(value = "showPro")
    @ResponseBody
    public String showAllPro() {
        JSONObject returnValue = new JSONObject();
        List<ProjectInfo> projectInfo = projectInfoService.selectByPState();
        returnValue.put("data", projectInfo);
        return JSON.toJSONString(returnValue);
    }

    @PostMapping(value = "showUser")
    @ResponseBody
    public String showUser() {
        JSONObject returnValue = new JSONObject();
        List<ProWorInfoMan> proWorInfoMan = proWorInfoManService.selectUser();
        returnValue.put("user", proWorInfoMan);
        return JSON.toJSONString(returnValue);
    }
}

