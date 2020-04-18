package com.len.mapper;

import com.len.base.BaseMapper;
import com.len.entity.ProWorInfoMan;

import java.util.List;

public interface ProWorInfoManMapper extends BaseMapper<ProWorInfoMan, String> {
    List<ProWorInfoMan> selectByUId(String u_id);

    List<ProWorInfoMan> selectByPId(String p_id);
}