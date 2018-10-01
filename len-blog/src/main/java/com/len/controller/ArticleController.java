package com.len.controller;

import com.len.entity.BlogArticle;
import com.len.service.BlogArticleService;
import com.len.util.JsonUtil;
import com.len.util.ReType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章管理（后台）
 *
 * @author JamesZBL
 * @email 1146556298@qq.com
 * @date 2018-05-05
 */
@RestController
@RequestMapping("/blog")
public class ArticleController {

    @Autowired
    private BlogArticleService articleService;


    @GetMapping("/articleList")
    public String articleListPage() {
        return "articleList";
    }


    @GetMapping("/header")
    public List<String> showArticles() {
        List<String> list = new ArrayList<>(4);
        list.add("java");
        list.add("架构");
        list.add("Linux");
        list.add("其他");
        return list;
    }

    @GetMapping("/article/list")
    public ReType getList(BlogArticle article, Integer page, Integer limit){
        limit=limit>100?100:limit;
        return articleService.show(article,page,limit);
    }
}
