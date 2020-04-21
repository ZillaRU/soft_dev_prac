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

    //得到是否有一模一样的情况
    public int selectSameCondi(ProWorInfoMan worInfo);

    // 更新项目人员
    public int updateRoleById(ProWorInfoMan worInfo);

    List<ProWorInfoMan> selectByProId(String proId);

    List<ProWorInfoMan> selectMyProIds(ProWorInfoMan worInfo);

    // 查找某一项目的某一角色的所有用户
    List<ProWorInfoMan> selectUserByRoleName(ProWorInfoMan worInfo);

    List<ProWorInfoMan> selectUser();

    //根据userId和proId查找出该用户在该项目里承担的角色
    List<ProWorInfoMan> selectRoleByUserId(ProWorInfoMan worInfo);

    //找到某项目下的所有user
    List<ProWorInfoMan> selectUserByProId(String proId);
}
