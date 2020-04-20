package com.len.service;

import com.len.base.BaseService;
import com.len.entity.RskReUsr;

import java.util.List;

public interface RskReUsrService extends BaseService<RskReUsr, String> {
    List<RskReUsr> selectByRId(String r_id);

    List<RskReUsr> selectByUId(String u_id);

    int deleteByRId(String r_id);
}
