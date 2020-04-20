package com.len.mapper;

import com.len.base.BaseMapper;
import com.len.entity.ProjectInfo;

import java.util.List;
//@Mapper
public interface ProjectInfoMapper extends BaseMapper<ProjectInfo, String> {
    List<ProjectInfo> selectByPmId(String pm_id);

    List<ProjectInfo> selectByEPGIdandState(String user_id);

    List<ProjectInfo> selectByPState();
}