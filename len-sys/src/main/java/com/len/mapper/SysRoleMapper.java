package com.len.mapper;

import com.len.base.BaseMapper;
import com.len.entity.SysRole;
import java.util.List;

public interface SysRoleMapper  extends BaseMapper<SysRole,String>{

    @Override
    int deleteByPrimaryKey(String id);

    @Override
    int insert(SysRole record);

    @Override
    int insertSelective(SysRole record);

    @Override
    SysRole selectByPrimaryKey(String id);

    @Override
    int updateByPrimaryKeySelective(SysRole record);

    @Override
    int updateByPrimaryKey(SysRole record);

    @Override
    List<SysRole> selectListByPage(SysRole sysRole);
}