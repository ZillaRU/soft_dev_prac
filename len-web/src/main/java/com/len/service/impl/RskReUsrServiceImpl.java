package com.len.service.impl;

import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.RskReUsr;
import com.len.mapper.RskReUsrMapper;
import com.len.service.RskReUsrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RskReUsrServiceImpl extends BaseServiceImpl<RskReUsr, String> implements RskReUsrService {
    @Autowired
    private RskReUsrMapper rskReUsrMapper;

    @Override
    public BaseMapper<RskReUsr, String> getMappser() {
        return rskReUsrMapper;
    }

    @Override
    public List<RskReUsr> selectByRId(String r_id) {
        return rskReUsrMapper.selectByRId(r_id);
    }

    @Override
    public List<RskReUsr> selectByUId(String u_id) {
        return rskReUsrMapper.selectByRId(u_id);
    }

    @Override
    public int deleteByRId(String r_id) {
        return rskReUsrMapper.deleteByRId(r_id);
    }

}
