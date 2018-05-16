package com.len.mapper;

import com.len.base.BaseMapper;
import com.len.entity.SysDictItem;

public interface SysDictItemMapper extends BaseMapper<SysDictItem,String>{
    int deleteByPrimaryKey(String id);

    int insert(SysDictItem record);

    int insertSelective(SysDictItem record);

    SysDictItem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysDictItem record);

    int updateByPrimaryKey(SysDictItem record);
}