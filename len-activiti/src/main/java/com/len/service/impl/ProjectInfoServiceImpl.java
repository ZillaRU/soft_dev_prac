package com.len.service.impl;

import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.ProjectInfo;
import com.len.mapper.ProjectInfoMapper;
import com.len.service.ProjectInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectInfoServiceImpl extends BaseServiceImpl<ProjectInfo, String> implements ProjectInfoService {

    @Autowired
    private ProjectInfoMapper projectInfoMapper;

    @Override
    public BaseMapper<ProjectInfo, String> getMappser() {
        return projectInfoMapper;
    }

    @Override
    public List<ProjectInfo> selectByPmId(String pm_id) {
        return projectInfoMapper.selectByPmId(pm_id);
    }

    @Override
    public List<ProjectInfo> selectByEPGIdandState(String user_id) {
        return projectInfoMapper.selectByEPGIdandState(user_id);
    }

    @Override
    public List<ProjectInfo> selectByPState() {
        return projectInfoMapper.selectByPState();
    }
}
