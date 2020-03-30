package com.len.controller;

import com.alibaba.fastjson.JSON;
import com.len.core.shiro.Principal;
import com.len.entity.ProjectInfo;
import com.len.entity.SysUser;
import com.len.exception.MyException;
import com.len.service.ProjectInfoService;
import com.len.service.SysUserService;
import com.len.util.JsonUtil;
import com.len.util.ReType;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricVariableUpdate;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
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
import java.util.Map;


@Controller
@RequestMapping(value = "/project")
public class ProjectInfoController {
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private SysUserService userService;

    @GetMapping("showApply")
    public String applyProject(Model model) {
        String id = Principal.getPrincipal().getId();
        SysUser user = userService.selectByPrimaryKey(id);
        model.addAttribute("user", user);
        return "act/project/apply-proj";
    }

    @ApiOperation(value = "申报项目", httpMethod = "POST")
    @Log(desc = "申报项目")
    @PostMapping("applyProject")
    @ResponseBody
    public JsonUtil applyProject(ProjectInfo projectInfo) {
        JsonUtil j = new JsonUtil();
        String msg = "项目信息添加成功";
        System.out.println(projectInfo.getStartDate() + " " + projectInfo.getEndDate());
        projectInfo.setProjState(0);
        projectInfo.setUserId(projectInfo.getPmId());
        projectInfo.setUserName(projectInfo.getPmName());
        projectInfo.setPmId(Principal.getPrincipal().getId());
        System.out.println(projectInfo.toString());
        try {
            projectInfoService.insertSelective(projectInfo);
        } catch (MyException e) {
            msg = "保存失败";
            j.setFlag(false);
            e.printStackTrace();
        }
        j.setMsg(msg);
        return j;
    }

    @GetMapping("showPMproject")
    public String showPMproject(Model model) {
        String id = Principal.getPrincipal().getId();
        SysUser user = userService.selectByPrimaryKey(id);
        model.addAttribute("user", user);
        return "act/project/my-pm-project";
    }

    @ApiOperation(value = "某用户主管的项目", httpMethod = "POST")
    @Log(desc = "主管项目")
    @GetMapping("showPMprojctList")
    @ResponseBody
    public ReType showPMprojctList(Model model) {
        List<ProjectInfo> list = projectInfoService.selectByPmId(Principal.getPrincipal().getId());
        return new ReType(list.size(), list);
    }

    @GetMapping("showProjDetail")
    public String showProjDetail(Model model, String projId) {
        ProjectInfo projectInfo = projectInfoService.selectByPrimaryKey(projId);
        System.out.println(JSON.toJSONString(projectInfo));
        model.addAttribute("projectDetail", projectInfo);
        return "act/project/projDetail";
    }

//    @GetMapping("showApprovals")
//    public String showApprovals(Model model) {
//
//    }

}
