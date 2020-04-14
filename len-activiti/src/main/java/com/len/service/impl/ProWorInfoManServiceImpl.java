package com.len.service.impl;

import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.ProWorInfoMan;
import com.len.mapper.ProWorInfoManMapper;
import com.len.service.ProWorInfoManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProWorInfoManServiceImpl extends BaseServiceImpl<ProWorInfoMan, String> implements ProWorInfoManService {

    @Autowired
    private ProWorInfoManMapper proWorInfoManMapper;

    @Override
    public BaseMapper<ProWorInfoMan, String> getMappser() { return proWorInfoManMapper; }

    public List<ProWorInfoMan> selectByPmId(String pro_id) {
        return proWorInfoManMapper.selectByPmId(pro_id);
    }

    public List<ProWorInfoMan> selectByProName(ProWorInfoMan worInfo) {
        return proWorInfoManMapper.selectByProName(worInfo);
    }

    public int delById(String id){
        return proWorInfoManMapper.delById(id);
    }

    public int insertProWor(ProWorInfoMan worInfo){
        return proWorInfoManMapper.insertProWor(worInfo);
    }

}
