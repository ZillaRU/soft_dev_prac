package com.len.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "proj_func")
@Data
public class ProjectFunction {
    @Id
    @Column(name = "proj_id")
    private String projId;

    @Column(name = "func_id")
    @Excel(name = "功能编号")
    private String funcId; // pattern: 一级4位二级4位 00230045 二级全0为一级功能名称

    @Column(name = "func_name")
    @Excel(name = "功能名称")
    private String funcName;

    public ProjectFunction() {
    }

    public ProjectFunction(String projId, String funcName) {
        this.projId = projId;
        this.funcName = funcName;
    }
}
