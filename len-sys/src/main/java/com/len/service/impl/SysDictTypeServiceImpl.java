package com.len.service.impl;

import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.SysDictType;
import com.len.mapper.SysDictTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhuxiaomeng
 * @date 2018/5/10.
 * @email 154040976@qq.com
 */
public class SysDictTypeServiceImpl extends BaseServiceImpl<SysDictType, String> {

    @Autowired
    private SysDictTypeMapper sysDictTypeMapper;

    @Override
    public BaseMapper<SysDictType, String> getMappser() {
        return sysDictTypeMapper;
    }
}
