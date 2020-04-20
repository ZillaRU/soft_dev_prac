package com.len.service;

import com.len.base.BaseService;
import com.len.entity.RskInfo;

import java.util.List;


public interface RiskInfoService extends BaseService<RskInfo, String> {
    List<RskInfo> selectByPmId(String pm_id);
}
