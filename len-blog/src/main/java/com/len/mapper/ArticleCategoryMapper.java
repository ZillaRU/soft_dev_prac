package com.len.mapper;

import com.len.base.BaseMapper;
import com.len.entity.ArticleCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleCategoryMapper extends BaseMapper<ArticleCategory, String> {

    void delByIds(@Param("ids") List<String> ids);
}