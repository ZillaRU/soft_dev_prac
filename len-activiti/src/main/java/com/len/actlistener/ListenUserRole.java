package com.len.actlistener;

import com.len.entity.SysRole;
import com.len.entity.SysRoleUser;
import com.len.entity.SysUser;
import com.len.service.ActAssigneeService;
import com.len.service.SysUserService;
import com.len.util.JsonUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhuxiaomeng
 * @date 2018/1/25.
 * @email 154040976@qq.com
 *
 * 切入实现系统用户 角色 用户-角色 同步到 activiti 用户 组 用户组 同步到工作流,模块化 无侵入
 *
 *
 */
@Aspect
@Component
public class ListenUserRole {

  @Autowired
  IdentityService identityService;

  @Autowired
  SysUserService userService;

  /**********************用户处理begin***************************/
  /**
   * 明确切入方法的参数
   * @param joinPoint
   */
  @Around("execution(com.len.util.JsonUtil com.len.controller.UserController.updateUser(..))")
  public Object listenerUserUpdate(ProceedingJoinPoint joinPoint){
    Object o=new Object();
    try{
      //更新前拿到用户-角色数据
      SysRoleUser sysRoleUser =new SysRoleUser();
      Object[] args = joinPoint.getArgs();
      sysRoleUser.setUserId(((SysUser)args[0]).getId());
      List<SysRoleUser> keyList=userService.selectByCondition(sysRoleUser);
      List<String> strings=new ArrayList<>();
      keyList.forEach(sysRoleUser1 -> strings.add(sysRoleUser1.getRoleId()));
      o = joinPoint.proceed(joinPoint.getArgs());
      JsonUtil jsonUtil= (JsonUtil) o;
      if(jsonUtil.isFlag()){
        changeUser(args,strings);
      }
    }catch (Throwable  e){
      e.printStackTrace();
    }
    return o;
  }

  /**
   * 新增用户监听 同步工作流用户表 环绕注解能得到 插入用户id 啊哈哈
   * @param joinPoint
   */
  @Around("execution(java.lang.String com.len.controller.UserController.addUser(..))")
  public Object  listenerUserInsert(ProceedingJoinPoint joinPoint){
    Object o=new Object();
    try{
      o = joinPoint.proceed(joinPoint.getArgs());
      Object[] args = joinPoint.getArgs();
      if(args.length==2){
        JsonUtil jsonUtil= (JsonUtil) o;
        if(jsonUtil.isFlag()){
          changeUser(args, Arrays.asList((String[]) args[1]));
        }
      }
    }catch (Throwable  e){
      e.printStackTrace();
    }
    return o;
  }

  @Around("execution(com.len.util.JsonUtil com.len.controller.UserController.del(..))")
  public Object listenDelUser(ProceedingJoinPoint point){
    Object o=new Object();
    try{
      o = point.proceed(point.getArgs());
      JsonUtil util= (JsonUtil) o;
      if(util.isFlag()){
        Object[] args = point.getArgs();
        identityService.deleteUser((String) args[0]);
      }

    }catch (Throwable  e){
      e.printStackTrace();
    }
    return o;
  }


  /**
   * 保存进 activiti 用户 角色 用户角色中间表
   * @param obj
   */
  private void changeUser(Object[] obj,List<String> strings){
    SysUser user= (SysUser) obj[0];
    identityService.deleteUser(user.getId());
    User au =new UserEntity();
    au.setId(user.getId());
    au.setFirstName(user.getRealName());
    au.setEmail(user.getEmail());
    identityService.saveUser(au);

    //删除用户-组关联
    for(String roleId:strings){
      identityService.deleteMembership(user.getId(),roleId);
    }
    //再次关联
    if(strings!=null){
      for(String roleId:strings){
        identityService.createMembership(user.getId(),roleId);
      }
    }
  }

  /**********************用户处理end***************************/



  /**********************角色处理begin***************************/
  @Around("execution(com.len.util.JsonUtil com.len.controller.RoleController.addRole(..))")
  public Object listenRoleInsert(ProceedingJoinPoint joinPoint){
    Object o=null;
    try{
      o=joinPoint.proceed(joinPoint.getArgs());
      JsonUtil j=(JsonUtil)o;
      if(j.isFlag()){
        Object[] args = joinPoint.getArgs();
        if(args.length==2){
          changeRole(args);
        }
      }
    }catch (Throwable throwable){

    }
    return o;
  }

  @Around("execution(com.len.util.JsonUtil com.len.controller.RoleController.updateUser(..))")
  public Object listenRoleUpdate(ProceedingJoinPoint joinPoint){
    Object o=null;
    try{
      o=joinPoint.proceed(joinPoint.getArgs());
      Object[] args = joinPoint.getArgs();
      if(args.length==2){
        if(((JsonUtil)o).isFlag()){
          changeRole(args);
        }
      }
    }catch (Throwable throwable){

    }
    return o;
  }

  @Around("execution(com.len.util.JsonUtil com.len.controller.RoleController.del(..))")
  public Object listenDelRole(ProceedingJoinPoint point){
    Object o=new Object();
    try{
      o = point.proceed(point.getArgs());
      JsonUtil util= (JsonUtil) o;
      if(util.isFlag()){
        Object[] args = point.getArgs();
        identityService.deleteGroup((String) args[0]);
      }

    }catch (Throwable  e){
      e.printStackTrace();
    }
    return o;
  }

  /**
   * 更新进组
   * @param obj
   */
  public void changeRole(Object[] obj){
    SysRole role = (SysRole) obj[0];
    identityService.deleteGroup(role.getId());
    Group group=new GroupEntity();
    group.setId(role.getId());
    group.setName(role.getRoleName());
    identityService.saveGroup(group);
  }

  /**********************角色处理end***************************/
}
