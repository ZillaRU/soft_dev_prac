package com.len.mapper;

import com.len.base.BaseMapper;
import com.len.model.BlogArticle;

public interface BlogArticleMapper extends BaseMapper<BlogArticle, String> {
    int deleteByPrimaryKey(String id);

    int insert(BlogArticle record);

    int insertSelective(BlogArticle record);

    BlogArticle selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BlogArticle record);

    int updateByPrimaryKey(BlogArticle record);
}