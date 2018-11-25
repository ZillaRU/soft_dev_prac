package com.len.mapper;

import com.len.base.BaseMapper;
import com.len.entity.BlogArticle;
import com.len.model.Article;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface BlogArticleMapper extends BaseMapper<BlogArticle, String> {

    List<Article> indexSelect();

    List<Article> selectArticle(@Param("code") String code);

    List<Article> selectArticleByTag(@Param("tagCode") String tagCode);

    BlogArticle selectPrevious(@Param("createDate")Date date);

    BlogArticle selectNext(@Param("createDate")Date date);
}