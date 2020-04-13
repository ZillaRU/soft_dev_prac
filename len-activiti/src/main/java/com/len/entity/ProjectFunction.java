package com.len.entity;

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
    private String funcId; // pattern: 一级4位二级4位 00230045 二级全0为一级功能名称

    @Column(name = "func_name")
    private String funcName;

    public ProjectFunction(String projId, String funcId, String funcName) {
        this.projId =  projId;
        this.funcId = funcId;
        this.funcName = funcName;
    }
}
