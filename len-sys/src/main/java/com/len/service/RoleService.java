package com.len.service;

import com.len.base.BaseService;
import com.len.entity.SysRole;
import com.len.util.JsonUtil;

import java.util.List;

/**
 * @author zhuxiaomeng
 * @date 2017/12/19.
 * @email 154040976@qq.com
 */
public interface RoleService extends BaseService<SysRole,String> {


  @Override
  int deleteByPrimaryKey(String id);

  @Override
  int insert(SysRole record);

  @Override
  int insertSelective(SysRole record);


  SysRole selectByPrimaryKey(String id);

  @Override
  int updateByPrimaryKeySelective(SysRole record);

  @Override
  int updateByPrimaryKey(SysRole record);

  @Override
  List<SysRole> selectListByPage(SysRole sysRole);

  public JsonUtil addRole(SysRole sysRole, String[] menus);

  public JsonUtil updateUser(SysRole role, String[] menus);

  public JsonUtil del(String id);
}
