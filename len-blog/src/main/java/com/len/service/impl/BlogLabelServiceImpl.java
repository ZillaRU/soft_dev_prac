package com.len.service.impl;

import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.BlogLabel;
import com.len.mapper.BlogLabelMapper;
import com.len.service.BlogLabelService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhuxiaomeng
 * @date 2018/7/28.
 * @email 154040976@qq.com
 */
@Service
public class BlogLabelServiceImpl extends BaseServiceImpl<BlogLabel, String> implements BlogLabelService {

    @Autowired
    private BlogLabelMapper blogLabelMapper;

    @Override
    public BaseMapper<BlogLabel, String> getMappser() {
        return blogLabelMapper;
    }

    @Override
    public int insertList(@Param("list") List<BlogLabel> blogLabels) {
        return blogLabelMapper.insertList(blogLabels);
    }
}
