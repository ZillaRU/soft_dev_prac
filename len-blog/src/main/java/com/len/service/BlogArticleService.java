package com.len.service;

import com.len.base.BaseService;
import com.len.entity.BlogArticle;
import com.len.model.Article;
import com.len.util.JsonUtil;

import java.util.Date;
import java.util.List;

/**
 * @author zhuxiaomeng
 * @date 2018/9/9.
 * @email 154040976@qq.com
 */
public interface BlogArticleService extends BaseService<BlogArticle, String> {

    List<Article> indexSelect();

    public JsonUtil getDetail(String code);

    public JsonUtil detail(String code,String ip);

    List<Article> selectArticle(String code);

    List<Article> selectArticleByTag(String tagCode);

    BlogArticle selectPrevious(Date date);

    BlogArticle selectNext(Date date);

}
