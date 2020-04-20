package com.len.service.impl;

import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.EquInfo;
import com.len.mapper.EquInfoMapper;
import com.len.service.EquInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquInfoServiceImpl extends BaseServiceImpl<EquInfo, String> implements EquInfoService {

    @Autowired
    private EquInfoMapper equInfoMapper;

    @Override
    public BaseMapper<EquInfo, String> getMappser() {
        return equInfoMapper;
    }

}


