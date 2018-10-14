package com.len.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.len.entity.ArticleList;
import com.len.entity.BlogArticle;
import com.len.service.BlogArticleService;
import com.len.util.JsonUtil;
import com.len.util.ReType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 文章管理（后台）
 *
 * @author JamesZBL
 * @email 154040976@qq.com
 * @date 2018-05-05
 */
@RestController
@RequestMapping("/blog")
public class ArticleController {

    @Autowired
    private BlogArticleService articleService;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
    public ReType getList(BlogArticle article, Integer page, Integer limit) {
        limit = limit > 100 ? 100 : limit;
        Page<Object> startPage = PageHelper.startPage(page, limit);
        List<BlogArticle> articles = articleService.selectListByPage(article);

        List<ArticleList> articleLists = new ArrayList<>();
        articles.forEach(s -> articleLists.add(
                new ArticleList(s.getId(), s.getCode(), s.getTitle(),
                        s.getTopNum(), s.getCreateBy(),
                        format.format(s.getCreateDate()), s.getContent())
                )
        );
        return new ReType(startPage.getTotal(), startPage.getPageNum(), articleLists);
    }

    @GetMapping("/article/getDetail/{code}")
    public JsonUtil getDetail(@PathVariable("code") String code) {
        return articleService.getDetail(code);
    }
}
