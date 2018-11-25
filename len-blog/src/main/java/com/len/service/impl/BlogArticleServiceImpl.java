package com.len.service.impl;

import com.github.pagehelper.PageHelper;
import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.*;
import com.len.mapper.BlogArticleMapper;
import com.len.model.Article;
import com.len.model.SimpleArticle;
import com.len.redis.RedisService;
import com.len.service.*;
import com.len.util.BeanUtil;
import com.len.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private RedisService redisService;

    @Autowired
    private SysUserService sysUserService;


    @Override
    public BaseMapper<BlogArticle, String> getMappser() {
        return blogArticleMapper;
    }

    private ArticleDetail getArticleByCode(String code) {
        Condition condition = new Condition(BlogArticle.class);
        condition.createCriteria().andEqualTo("code", code)
                .andEqualTo("delFlag", 0);
        List<BlogArticle> articles = selectByExample(condition);
        if (articles.isEmpty()) {
            return null;
        }
        ArticleDetail detail = new ArticleDetail();
        BlogArticle blogArticle = articles.get(0);


        Article article = new Article();
        BeanUtil.copyNotNullBean(blogArticle, article);
        detail.setArticle(article);

        String createBy = blogArticle.getCreateBy();
        if (!StringUtils.isEmpty(createBy)) {
            SysUser sysUser = sysUserService.selectByPrimaryKey(createBy);
            if (sysUser != null) {
                article.setCreateName(sysUser.getUsername());
            }
        } else {
            article.setCreateName("admin");
        }

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
        return detail;
    }

    @Override
    public List<Article> indexSelect() {
        return blogArticleMapper.indexSelect();
    }

    @Override
    public JsonUtil getDetail(String code) {
        JsonUtil json = new JsonUtil();
        ArticleDetail detail = getArticleByCode(code);
        if (detail == null) {
            json.setStatus(404);
            json.setFlag(false);
            return json;
        }
        json.setData(detail);
        json.setFlag(true);
        return json;

    }

    @Override
    public JsonUtil detail(String code, String ip) {
        JsonUtil json = new JsonUtil();
        ArticleDetail detail = getArticleByCode(code);
        if (detail == null) {
            json.setStatus(404);
            json.setFlag(false);
            return json;
        }
        Article article = detail.getArticle();
        //点击次数
        int clickNum = addArticleReadNum(ip, article.getId());
        if (clickNum > 0) {
            article.setReadNumber(clickNum);
        }

        //上一篇
        PageHelper.startPage(1, 1);
        BlogArticle previous = selectPrevious(article.getCreateDate());
        if (previous != null) {
            SimpleArticle simpleArticle = new SimpleArticle();
            BeanUtil.copyNotNullBean(previous, simpleArticle);
            detail.setPrevious(simpleArticle);
        }
        //下一篇
        PageHelper.startPage(1, 1);
        BlogArticle next = selectNext(article.getCreateDate());
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
    public List<Article> selectArticle(String code) {
        return blogArticleMapper.selectArticle(code);
    }

    @Override
    public List<Article> selectArticleByTag(String tagCode) {
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

    /**
     * 半小时增加一次有效点击数
     *
     * @param ip        访问者ip
     * @param articleId 文章id
     */
    private int addArticleReadNum(String ip, String articleId) {
        String str = ip + "_" + articleId;
        if (!StringUtils.isBlank(str)) {
            if (StringUtils.isEmpty(redisService.get(str))) {
                redisService.set(str, "true", 60 * 30L);
                BlogArticle article = selectByPrimaryKey(articleId);
                article.setReadNumber(article.getReadNumber() + 1);
                updateByPrimaryKey(article);
                return article.getReadNumber();
            }
        }
        return -1;
    }
}
