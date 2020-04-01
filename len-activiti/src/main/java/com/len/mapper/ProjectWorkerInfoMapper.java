package com.len.mapper;

import com.len.base.BaseMapper;
import com.len.entity.ProjectWorkerInfo;

import java.util.List;


public interface ProjectWorkerInfoMapper extends BaseMapper<ProjectWorkerInfo,String>{
    List<ProjectWorkerInfo> selectByPmId(String pm_id);
}
