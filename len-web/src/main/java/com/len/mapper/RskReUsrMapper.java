package com.len.mapper;

import com.len.base.BaseMapper;
import com.len.entity.RskReUsr;

import java.util.List;

public interface RskReUsrMapper extends BaseMapper<RskReUsr, String> {
    List<RskReUsr> selectByRId(String r_id);

    List<RskReUsr> selectByUId(String u_id);

    int deleteByRId(String r_id);
}