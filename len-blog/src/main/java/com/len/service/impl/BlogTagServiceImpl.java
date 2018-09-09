package com.len.service.impl;

import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.BlogTag;
import com.len.mapper.BlogTagMapper;
import com.len.service.BlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhuxiaomeng
 * @date 2018/7/28.
 * @email 154040976@qq.com
 */
@Service
public class BlogTagServiceImpl extends BaseServiceImpl<BlogTag, String> implements BlogTagService {

    @Autowired
    private BlogTagMapper blogTagMapper;

    @Override
    public BaseMapper<BlogTag, String> getMappser() {
        return blogTagMapper;
    }
}
