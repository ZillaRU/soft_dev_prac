package com.len.controller;

import com.len.entity.BlogArticle;
import com.len.service.BlogArticleService;
import com.len.util.ReType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuxiaomeng
 * @date 2018/10/1.
 * @email 154040976@qq.com
 * <p>
 * 博客后台业务管理
 */
@RestController
@RequestMapping("/blog-admin")
public class BlogAdminController {

    @Autowired
    private BlogArticleService articleService;

    @GetMapping("/article/getList")
    public ReType getArticleList(BlogArticle article, Integer page, Integer limit) {
        return articleService.getList(article, page, limit);
    }
}
