package com.len.service.impl;

import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.ProjectWorkerInfo;
import com.len.mapper.ProjectWorkerInfoMapper;
import com.len.service.ProjectWorkerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectWorkerInfoServiceImpl extends BaseServiceImpl<ProjectWorkerInfo, String> implements ProjectWorkerInfoService {

    @Autowired
    private ProjectWorkerInfoMapper projectWorkerInfoMapper;

    @Override
    public BaseMapper<ProjectWorkerInfo, String> getMappser() { return projectWorkerInfoMapper; }

    @Override
    public List<ProjectWorkerInfo> selectByPmId(String pm_id) {
        return projectWorkerInfoMapper.selectByPmId(pm_id);
    }

    public List<ProjectWorkerInfo> selectByProName(ProjectWorkerInfo worInfo) {
        return projectWorkerInfoMapper.selectByProName(worInfo);
    }

    public ProjectWorkerInfo selectByProId(String pro_id){
        return projectWorkerInfoMapper.selectByProId(pro_id);
    }

    public int updateProStatus(ProjectWorkerInfo worInfo){return projectWorkerInfoMapper.updateProStatus(worInfo);}
}
