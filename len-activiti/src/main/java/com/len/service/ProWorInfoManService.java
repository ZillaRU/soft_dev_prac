package com.len.service;

import com.len.base.BaseService;
import com.len.entity.ProWorInfoMan;

import java.util.List;

public interface ProWorInfoManService extends BaseService<ProWorInfoMan, String> {
    List<ProWorInfoMan> selectByPmId(String pmId);

    List<ProWorInfoMan> selectByProName(ProWorInfoMan worInfo);

    public int delById(String id);

    public int insertProWor(ProWorInfoMan worInfo);

    public int selectRoleNum(ProWorInfoMan worInfo);

    List<ProWorInfoMan> selectByUId(String u_id);

    List<ProWorInfoMan> selectByPId(String p_id);

    // 更新项目人员
    public int updateRoleById(ProWorInfoMan worInfo);

    List<ProWorInfoMan> selectByProId(String proId);

    // 查找某一项目的某一角色的所有用户
    List<ProWorInfoMan> selectUserByRoleName(ProWorInfoMan worInfo);

    List<ProWorInfoMan> selectUser();
}
