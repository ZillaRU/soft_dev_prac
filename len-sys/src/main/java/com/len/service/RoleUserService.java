package com.len.service;

import com.len.base.BaseService;
import com.len.entity.SysRoleUser;

import java.util.List;

/**
 * @author zhuxiaomeng
 * @date 2017/12/21.
 * @email 154040976@qq.com
 */
public interface RoleUserService  extends BaseService<SysRoleUser,String>{

  int deleteByPrimaryKey(SysRoleUser sysRoleUser);

  @Override
  int insert(SysRoleUser sysRoleUser);

  int selectCountByCondition(SysRoleUser sysRoleUser);

  List<SysRoleUser> selectByCondition(SysRoleUser sysRoleUser);

  // list of role_id
  List<String> selectByUserId(String user_id);
}
