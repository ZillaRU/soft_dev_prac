package com.len.base.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.len.base.BaseMapper;
import com.len.base.BaseService;
import com.len.base.CurrentUser;
import com.len.exception.MyException;
import com.len.util.ReType;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuxiaomeng
 * @date 2017/12/13.
 * @email 154040976@qq.com
 */
public abstract class BaseServiceImpl <T,E extends Serializable> implements BaseService<T,E> {

  private static Logger logger= LoggerFactory.getLogger(BaseServiceImpl.class);

  public abstract BaseMapper<T,E> getMappser();

  @Override
  public int deleteByPrimaryKey(E id) {
    return getMappser().deleteByPrimaryKey(id);
  }

  @Override
  public int insert(T record) {
    record=addValue(record,true);
    return getMappser().insert(record);
  }

  /**
   * 通用注入创建 更新信息 可通过super调用
   * @param record
   * @param flag
   * @return
   */
  public T  addValue(T record,boolean flag){
    CurrentUser currentUser= (CurrentUser) SecurityUtils.getSubject().getSession().getAttribute("curentUser");
    //统一处理公共字段
    Class<?> clazz=record.getClass();
    try {
      if(flag){
        Field field=clazz.getDeclaredField("createBy");
        field.setAccessible(true);
        field.set(record,currentUser.getId());
        Field fieldDate=clazz.getDeclaredField("createDate");
        fieldDate.setAccessible(true);
        fieldDate.set(record,new Date());
      }else{
        Field field=clazz.getDeclaredField("updateBy");
        field.setAccessible(true);
        field.set(record,currentUser.getId());
        Field fieldDate=clazz.getDeclaredField("updateDate");
        fieldDate.setAccessible(true);
        fieldDate.set(record,new Date());
      }
    } catch (NoSuchFieldException e) {
      //无此字段
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return record;
  }

  @Override
  public int insertSelective(T record) {
    record=addValue(record,true);
    return getMappser().insertSelective(record);
  }

  @Override
  public T selectByPrimaryKey(E id) {
    return getMappser().selectByPrimaryKey(id);
  }

  @Override
  public int updateByPrimaryKeySelective(T record) {
    record=addValue(record,false);
    return getMappser().updateByPrimaryKeySelective(record);
  }

  @Override
  public int updateByPrimaryKey(T record) {
    record=addValue(record,false);
    return getMappser().updateByPrimaryKey(record);
  }

  @Override
  public List<T> selectListByPage(T record){
      return getMappser().selectListByPage(record);
  }

  /**
   * 公共展示类
   * @param t 实体
   * @param page 页
   * @param limit 行
   * @return
   */
  @Override
  public String  show(T t,int page,int limit){
    List<T> tList=null;
    Page<T> tPage= PageHelper.startPage(page,limit);
    try{
      tList=getMappser().selectListByPage(t);
    }catch (MyException e){
      logger.error("class:BaseServiceImpl ->method:show->message:"+e.getMessage());
      e.printStackTrace();
    }
    ReType reType=new ReType(tPage.getTotal(),tList);
    return JSON.toJSONString(reType);
  }

}
