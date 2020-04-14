package com.len.service;

import com.len.base.BaseService;
import com.len.entity.ProWorInfoMan;

import java.util.List;

public interface ProWorInfoManService extends BaseService<ProWorInfoMan, String> {
    List<ProWorInfoMan> selectByPmId(String pmId);

    List<ProWorInfoMan> selectByProName(ProWorInfoMan worInfo);

    public int delById(String id);

    public int insertProWor(ProWorInfoMan worInfo);
}
