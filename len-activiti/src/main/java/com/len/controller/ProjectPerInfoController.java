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
@RequestMapping(value = "/projectPer")

public class ProjectPerInfoController {
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private SysUserService userService;

    @ApiOperation(value = "某用户主管的项目", httpMethod = "GET")
    @Log(desc = "主管项目")
    @GetMapping("showPMprojctList")
    @ResponseBody
    public ReType showPMprojctList() {
        List<ProjectInfo> list = projectInfoService.selectByPmId(Principal.getPrincipal().getId());
        return new ReType(list.size(), list);
    }

}


