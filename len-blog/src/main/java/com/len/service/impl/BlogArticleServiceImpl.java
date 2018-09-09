package com.len.service.impl;

import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.BlogArticle;
import com.len.mapper.BlogArticleMapper;
import com.len.service.BlogArticleService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhuxiaomeng
 * @date 2018/9/9.
 * @email 154040976@qq.com
 */
public class BlogArticleServiceImpl extends BaseServiceImpl<BlogArticle, String> implements BlogArticleService {

    @Autowired
    private BlogArticleMapper blogArticleMapper;

    @Override
    public BaseMapper<BlogArticle, String> getMappser() {
        return blogArticleMapper;
    }
}
