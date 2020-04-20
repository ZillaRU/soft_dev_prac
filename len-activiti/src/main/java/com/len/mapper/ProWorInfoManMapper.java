package com.len.mapper;

import com.len.base.BaseMapper;
import com.len.entity.ProWorInfoMan;

import java.util.List;

public interface ProWorInfoManMapper extends BaseMapper<ProWorInfoMan,String> {

    //按照项目id搜索出该项目下的开发人员
    List<ProWorInfoMan> selectByPmId(String pmId);

    // 支持项目名称的模糊搜索
    List<ProWorInfoMan> selectByProName(ProWorInfoMan worInfo);

    //按照id删除某项目下的某成员
    public int delById(String Id);

    //插入项目人员
    public int insertProWor(ProWorInfoMan worInfo);

    //得到角色的个数
    public int selectRoleNum(ProWorInfoMan worInfo);

    // 更新项目人员
    public int updateRoleById(ProWorInfoMan worInfo);

    List<ProWorInfoMan> selectByUId(String u_id);

    List<ProWorInfoMan> selectByPId(String p_id);

    //搜索出该项目下的人员
    List<ProWorInfoMan> selectByProId(String proId);

    // 查找出某一角色下的所有成员
    List<ProWorInfoMan> selectUserByRoleName(ProWorInfoMan worInfo);

    List<ProWorInfoMan> selectUser();
}
