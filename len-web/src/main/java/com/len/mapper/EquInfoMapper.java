package com.len.mapper;

import com.len.base.BaseMapper;
import com.len.entity.EquInfo;

import java.util.List;

public interface EquInfoMapper extends BaseMapper<EquInfo, String> {
    List<EquInfo> selectByRId(String rid);
}