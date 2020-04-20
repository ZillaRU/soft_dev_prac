package com.len.service;

import com.len.base.BaseService;
import com.len.entity.ProjectWorkerInfo;

import java.util.List;

public interface ProjectWorkerInfoService extends BaseService<ProjectWorkerInfo, String> {
    List<ProjectWorkerInfo> selectByPmId(String pm_id);

    List<ProjectWorkerInfo> selectByProName(ProjectWorkerInfo worInfo);

    public ProjectWorkerInfo selectByProId(String pro_id);

    // 更新项目状态
    public int updateProStatus(ProjectWorkerInfo projectWorkerInfo);

}
