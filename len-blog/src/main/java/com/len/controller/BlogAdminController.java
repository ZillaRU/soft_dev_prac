package com.len.controller;

import com.len.entity.BlogArticle;
import com.len.service.BlogArticleService;
import com.len.util.JsonUtil;
import com.len.util.ReType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;

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

    /**
     * 根据code获取文章明细
     *
     * @param code
     * @return
     */
    @GetMapping("/article/getDetail/{code}")
    public JsonUtil getDetail(@PathVariable("code") String code) {
        JsonUtil json = new JsonUtil();
        Condition condition = new Condition(BlogArticle.class);
        condition.createCriteria().andEqualTo("code", code);
        List<BlogArticle> articles = articleService.selectByExample(condition);
        if (articles.isEmpty()) {
            json.setStatus(404);
            json.setFlag(false);
            return json;
        }
        json.setData(articles.get(0));
        json.setFlag(true);
        return json;
    }


}
