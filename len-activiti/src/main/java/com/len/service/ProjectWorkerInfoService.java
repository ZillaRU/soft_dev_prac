package com.len.service;

import com.len.base.BaseService;
import com.len.entity.ProjectWorkerInfo;
import com.len.entity.SysRoleUser;

import java.util.List;

public interface ProjectWorkerInfoService extends BaseService<ProjectWorkerInfo, String> {
    List<ProjectWorkerInfo> selectByPmId(String pm_id);


}
