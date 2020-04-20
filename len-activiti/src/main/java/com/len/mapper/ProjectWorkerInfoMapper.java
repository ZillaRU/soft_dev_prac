package com.len.mapper;

import com.len.base.BaseMapper;
import com.len.entity.ProjectWorkerInfo;

import java.util.List;


public interface ProjectWorkerInfoMapper extends BaseMapper<ProjectWorkerInfo,String>{
    // 按照项目id查找项目
    List<ProjectWorkerInfo> selectByPmId(String pm_id);

    // 支持项目名称的模糊搜索
    List<ProjectWorkerInfo> selectByProName(ProjectWorkerInfo worInfo);

    // 更新项目状态
    public int updateProStatus(String pro_status);
}
