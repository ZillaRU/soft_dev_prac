package com.len.service;

import com.len.base.BaseService;
import com.len.entity.ProjectInfo;

import java.util.List;

public interface ProjectInfoService extends BaseService<ProjectInfo, String> {
    List<ProjectInfo> selectByPmId(String pm_id);

    List<ProjectInfo> selectByEPGIdandState(String user_id);

    List<ProjectInfo> selectByPState();
}
