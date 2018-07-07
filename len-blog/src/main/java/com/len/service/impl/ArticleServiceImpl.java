package com.len.service.impl;

import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.mapper.BlogArticleMapper;
import com.len.model.BlogArticle;
import com.len.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 博客文章业务实现
 *
 * @author JamesZBL
 * @email 1146556298@qq.com
 * @date 2018-05-05
 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl<BlogArticle, String> implements ArticleService {

    @Autowired
    private BlogArticleMapper mapper;

    @Override
    public BaseMapper<BlogArticle, String> getMappser() {
        return mapper;
    }
}
