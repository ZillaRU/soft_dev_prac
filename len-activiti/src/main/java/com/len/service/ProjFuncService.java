package com.len.service;

import com.len.base.BaseService;
import com.len.entity.ProjectFunction;

import java.util.List;

public interface ProjFuncService extends BaseService<ProjectFunction, String> {
    List<ProjectFunction> selectByProjId(String proj_id);

    int updateByCoPrimaryKey(ProjectFunction func);
}
