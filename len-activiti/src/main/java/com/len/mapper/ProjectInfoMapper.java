package com.len.mapper;

import com.len.base.BaseMapper;
import com.len.entity.ProjectInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
//@Mapper
public interface ProjectInfoMapper extends BaseMapper<ProjectInfo,String> {
    List<ProjectInfo> selectByPmId(String pm_id);

    List<ProjectInfo> selectByPState();

//    /* 获取所有项目 */
//    List<ProjectInfo> getAllProj();
//
//    /* 获取参与的所有项目 */
//    List<ProjectInfo> getProjByCurrUser(String user_id);

//    /**
//     * 根据pm_chief_id获取所有项目
//     */
//    List<ProjectInfo> getProjByPmChief(String pmChiefId);

}