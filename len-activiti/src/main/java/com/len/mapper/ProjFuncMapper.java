package com.len.mapper;

import com.len.base.BaseMapper;
import com.len.entity.ProjectFunction;

import java.util.List;

public interface ProjFuncMapper extends BaseMapper<ProjectFunction, String> {
    List<ProjectFunction> selectByProjId(String proj_id);

    int updateByCoPrimaryKey(ProjectFunction func);
}
