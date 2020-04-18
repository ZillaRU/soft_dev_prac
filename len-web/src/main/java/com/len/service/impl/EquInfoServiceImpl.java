package com.len.service.impl;

import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.EquInfo;
import com.len.exception.MyException;
import com.len.mapper.EquInfoMapper;
import com.len.service.ProjectInfoService;
import com.len.service.EquInfoService;
import com.len.util.BeanUtil;
import com.len.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquInfoServiceImpl extends BaseServiceImpl<EquInfo, String> implements EquInfoService {

    @Autowired
    private EquInfoMapper equInfoMapper;

    @Override
    public BaseMapper<EquInfo, String> getMappser() {
        return equInfoMapper;
    }

}


