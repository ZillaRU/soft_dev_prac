package com.len.service.impl;

import com.github.pagehelper.PageHelper;
import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.*;
import com.len.mapper.BlogArticleMapper;
import com.len.model.SimpleArticle;
import com.len.service.ArticleCategoryService;
import com.len.service.ArticleTagService;
import com.len.service.BlogArticleService;
import com.len.service.BlogTagService;
import com.len.util.BeanUtil;
import com.len.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhuxiaomeng
 * @date 2018/9/9.
 * @email 154040976@qq.com
 */
@Service
public class BlogArticleServiceImpl extends BaseServiceImpl<BlogArticle, String> implements BlogArticleService {

    @Autowired
    private BlogArticleMapper blogArticleMapper;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Autowired
    private BlogTagService tagService;

    @Override
    public BaseMapper<BlogArticle, String> getMappser() {
        return blogArticleMapper;
    }

    @Override
    public JsonUtil getDetail(String code) {
        JsonUtil json = new JsonUtil();
        Condition condition = new Condition(BlogArticle.class);
        condition.createCriteria().andEqualTo("code", code);
        List<BlogArticle> articles = selectByExample(condition);
        if (articles.isEmpty()) {
            json.setStatus(404);
            json.setFlag(false);
            return json;
        }
        ArticleDetail detail = new ArticleDetail();
        BlogArticle blogArticle = articles.get(0);
        detail.setArticle(blogArticle);

        ArticleTag articleTag = new ArticleTag();
        articleTag.setArticleId(blogArticle.getId());
        List<ArticleTag> articleTags = articleTagService.select(articleTag);
        if (!articleTags.isEmpty()) {
            condition = new Condition(BlogTag.class);
            condition.createCriteria().andIn("id", articleTags.stream()
                    .map(ArticleTag::getTagId).collect(Collectors.toList()));
            List<BlogTag> blogTags = tagService.selectByExample(condition);

            detail.setTags(blogTags.stream().map(BlogTag::getTagCode).collect(Collectors.toList()));
        }
        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setArticleId(blogArticle.getId());
        List<ArticleCategory> articleCategories = articleCategoryService.select(articleCategory);
        if (!articleCategories.isEmpty()) {
            detail.setCategory(articleCategories.stream().map(ArticleCategory::getCategoryId)
                    .collect(Collectors.toList()));
        }
        //上一篇
        PageHelper.startPage(1, 1);
        BlogArticle previous = selectPrevious(blogArticle.getCreateDate());
        if (previous != null) {
            SimpleArticle simpleArticle = new SimpleArticle();
            BeanUtil.copyNotNullBean(previous, simpleArticle);
            detail.setPrevious(simpleArticle);
        }
        //下一篇
        PageHelper.startPage(1, 1);
        BlogArticle next = selectNext(blogArticle.getCreateDate());
        if (next != null) {
            SimpleArticle simpleArticle = new SimpleArticle();
            BeanUtil.copyNotNullBean(next, simpleArticle);
            detail.setNext(simpleArticle);
        }
        json.setData(detail);
        json.setFlag(true);
        return json;
    }

    @Override
    public List<BlogArticle> selectArticle(String code) {
        return blogArticleMapper.selectArticle(code);
    }

    @Override
    public List<BlogArticle> selectArticleByTag(String tagCode) {
        return blogArticleMapper.selectArticleByTag(tagCode);
    }

    @Override
    public BlogArticle selectPrevious(Date date) {
        return blogArticleMapper.selectPrevious(date);
    }

    @Override
    public BlogArticle selectNext(Date date) {
        return blogArticleMapper.selectNext(date);
    }
}
