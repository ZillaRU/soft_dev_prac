package com.len.mapper;

import com.len.base.BaseMapper;
import com.len.entity.SysDictType;

public interface SysDictTypeMapper extends BaseMapper<SysDictType, String> {
    int deleteByPrimaryKey(String id);

    int insert(SysDictType record);

    int insertSelective(SysDictType record);

    SysDictType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysDictType record);

    int updateByPrimaryKey(SysDictType record);
}