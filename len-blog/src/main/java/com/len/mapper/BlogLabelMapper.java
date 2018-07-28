package com.len.mapper;

import com.len.base.BaseMapper;
import com.len.entity.BlogLabel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogLabelMapper extends BaseMapper<BlogLabel, String> {

    @Override
    public int insertList(@Param("list") List<BlogLabel> blogLabels);
}