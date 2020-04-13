package com.len.controller;

import com.len.base.BaseController;
import com.len.entity.ProjectFunction;
import com.len.service.ProjFuncService;
import com.len.util.CommonUtil;
import com.len.util.ExcelUtil;
import com.len.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/excel")
public class ExcelController extends BaseController {
    @Autowired
    private ProjFuncService projFuncService;

    // 导入
    @PostMapping(value = "importData")
    JsonUtil importData(@RequestParam MultipartFile file, @RequestParam String projId) {
        JsonUtil resultJson = new JsonUtil();
        List<ProjectFunction> importData = null;
        try {
            importData = ExcelUtil.importExcel(file.getInputStream(), ProjectFunction.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null == importData) {
            resultJson.setStatus(1);
            resultJson.setMsg("导入失败！");
            return resultJson;
        }
        int num = 0;
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

    //导出
    @PostMapping(value = "exportData")
    JsonUtil exportData(@RequestParam String projId) {
        String excelTitle = projId + "功能";
        String path = "/export/company/excel/" +excelTitle + new Date().toString() + ".xls";;
        String realPath = CommonUtil.createFolderPath(path);
        return ExcelUtil.exportExcel(excelTitle, realPath, path, ProjectFunction.class, projFuncService.selectByProjId(projId));
    }

    //下载模板
    @PostMapping(value = "downloadTemplate")
    public JsonUtil downloadTemplate() {
//        String excelTitle = "项目功能模板";
//        String path = "/export/company/excel/" + new Date().toString() + ".xls";
//        String realPath = CommonUtil.createFolderPath(propertyUtil.getUploadPath() + path);
//        return ExcelUtil.exportExcel(excelTitle, realPath, path, ProjectFunction.class, projFuncService.getDownloadTemplate());
        return null;
    }
}
