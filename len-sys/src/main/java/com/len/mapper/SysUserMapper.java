package com.len.mapper;

import com.len.base.BaseMapper;
import com.len.entity.SysUser;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper extends BaseMapper<SysUser,String> {

    @Override
    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    @Override
    int insertSelective(SysUser record);

    @Override
    SysUser selectByPrimaryKey(String id);

    @Override
    int updateByPrimaryKeySelective(SysUser record);

    @Override
    int updateByPrimaryKey(SysUser record);

    SysUser login(@Param("username") String username);

    @Override
    List<SysUser> selectListByPage(SysUser sysUser);

    int count();

    int add(SysUser user);

    int delById(String id);

    int checkUser(String username);


}