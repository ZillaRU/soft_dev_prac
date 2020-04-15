package com.len.controller;

import com.len.base.BaseController;
import com.len.entity.ProjectFunction;
import com.len.service.ProjFuncService;
import com.len.service.ProjectInfoService;
import com.len.util.ExcelUtil;
import com.len.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/excel")
public class ExcelController extends BaseController {
    @Autowired
    private ProjFuncService projFuncService;

    @Autowired
    private ProjectInfoService projectInfoService;

    @RequestMapping("exportData")
    public void export(HttpServletResponse response, String projId){
        List<ProjectFunction> personList = projFuncService.selectByProjId(projId);
        String s = projectInfoService.selectByPrimaryKey(projId).getProjName();
        //导出操作
        ExcelUtil.exportExcel(personList, s+ "功能列表","功能列表",ProjectFunction.class,s + "功能列表"+ new Date().toString()+".xls",response);
    }


    // 导入
    @PostMapping(value = "importData")
    JsonUtil importData(@RequestParam MultipartFile file, @RequestParam String projId) {
        JsonUtil resultJson = new JsonUtil();
        List<ProjectFunction> importData = null;
        importData = ExcelUtil.importExcel(file, 1, 1, ProjectFunction.class);
        if (null == importData) {
            resultJson.setStatus(1);
            resultJson.setMsg("导入失败！");
            return resultJson;
        }
        int num = 0;
        projFuncService.deleteByPrimaryKey(projId);
        Set<String> functionIdSet = new HashSet<>();
        List<ProjectFunction> list = projFuncService.selectByProjId(projId);
        for (ProjectFunction i : list) {
            functionIdSet.add(i.getFuncId());
        }
        for (ProjectFunction func : importData) {
            num++;
            if (func.getFuncId().isEmpty()) {
                resultJson.setMsg("导入失败！第" + num + "行的编号不能为空");
                resultJson.setStatus(1);
                return resultJson;
            }
            if (functionIdSet.contains(func.getFuncId())) {
                resultJson.setStatus(1);
                resultJson.setMsg("导入中止！第" + num + "行的编号重复");
                resultJson.setData(null);
                return resultJson;
            }
            func.setProjId(projId);
            projFuncService.insert(func);
        }
        resultJson.setStatus(0);
        resultJson.setMsg("导入成功！");
        resultJson.setData(null);
        return resultJson;
    }

}
