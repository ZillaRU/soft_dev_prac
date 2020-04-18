package com.len.service.impl;

import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.ProjectFunction;
import com.len.mapper.ProjFuncMapper;
import com.len.service.ProjFuncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjFuncServiceImpl extends BaseServiceImpl<ProjectFunction, String> implements ProjFuncService {

    @Autowired
    private ProjFuncMapper projFuncMapper;

    @Override
    public BaseMapper<ProjectFunction, String> getMappser() {
        return projFuncMapper;
    }

    @Override
    public List<ProjectFunction> selectByProjId(String proj_id) {
        return projFuncMapper.selectByProjId(proj_id);
    }

    @Override
    public int updateByCoPrimaryKey(ProjectFunction func) {
        return projFuncMapper.updateByCoPrimaryKey(func);
    }
}
