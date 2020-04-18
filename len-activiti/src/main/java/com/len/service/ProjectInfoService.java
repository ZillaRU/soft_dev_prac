package com.len.service;

import com.len.base.BaseService;
import com.len.entity.ProjectInfo;
import com.len.entity.SysRoleUser;

import java.util.List;

public interface ProjectInfoService extends BaseService<ProjectInfo, String> {
    List<ProjectInfo> selectByPmId(String pm_id);

    List<ProjectInfo> selectByPState();
}
