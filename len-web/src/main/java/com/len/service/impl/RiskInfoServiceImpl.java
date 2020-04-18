package com.len.service.impl;

import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.RskInfo;
import com.len.mapper.RskInfoMapper;
import com.len.service.RiskInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskInfoServiceImpl extends BaseServiceImpl<RskInfo, String> implements RiskInfoService {

    @Autowired
    private RskInfoMapper rskInfoMapper;

    @Override
    public BaseMapper<RskInfo, String> getMappser() {
        return rskInfoMapper;
    }

    @Override
    public List<RskInfo> selectByPmId(String pm_id) {
        return rskInfoMapper.selectByPmId(pm_id);
    }

}

