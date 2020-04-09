package com.len.entity;

import com.excel.poi.annotation.ExportField;
import com.excel.poi.annotation.ImportField;
import lombok.Data;

@Data
public class ProjectFunction {
    @ExportField(columnName = "功能编号")
    @ImportField(required = true, regex = "[0-9]{4}[0-9]{4}", regexMessage = "功能编号格式：一级4位 二级4位")
    private String funcId; // pattern: 一级4位二级4位 00230045 二级全0为一级功能名称

    @ExportField(columnName = "功能名称")
    @ImportField(required = true)
    private String funcName;
}
