package com.len.mapper;

import com.len.base.BaseMapper;
import com.len.entity.BlogArticle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogArticleMapper extends BaseMapper<BlogArticle, String> {

    List<BlogArticle> selectArticle(@Param("code") String code);

    List<BlogArticle> selectArticleByTag(@Param("tagCode") String tagCode);
}