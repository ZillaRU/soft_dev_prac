package com.len.controller;

import com.len.entity.ArticleDetail;
import com.len.entity.BlogArticle;
import com.len.entity.BlogCategory;
import com.len.model.Article;
import com.len.service.*;
import com.len.util.JsonUtil;
import com.len.util.ReType;
import com.len.util.UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author zhuxiaomeng
 * @date 2018/10/1.
 * @email 154040976@qq.com
 * <p>
 * 博客后台业务管理
 */
@CrossOrigin
@RestController
@RequestMapping("/blog-admin")
@Slf4j
public class BlogAdminController {

    @Autowired
    private BlogArticleService articleService;

    @Value("${lenosp.imagePath}")
    private String imagePath;

    @Autowired
    private UploadUtil uploadUtil;

    @Autowired
    private BlogCategoryService categoryService;


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
        return articleService.getDetail(code);
    }

    @GetMapping("/article/getCategory")
    public JsonUtil getCategory() {
        JsonUtil json = new JsonUtil();
        List<BlogCategory> categories = categoryService.selectAll();
        categories.sort(Comparator.comparing(BlogCategory::getSequence));
        json.setData(categories);
        return json;
    }

    @PostMapping("/article/addImage")
    public JsonUtil addImage(MultipartHttpServletRequest request) {
        MultipartFile multipartFile = request.getFile("file");
        String path = uploadUtil.upload(multipartFile);
        JsonUtil json = new JsonUtil();
        StringBuffer requestURL = request.getRequestURL();
        int serverPort = request.getServerPort();
        int i = requestURL.indexOf(String.valueOf(serverPort));
        String url = requestURL.substring(0, i);
        json.setData(url + String.valueOf(serverPort) + "/img/" + path);
        json.setFlag(true);
        return json;
    }

    @PostMapping("/article/add")
    public JsonUtil addArticle(@RequestBody ArticleDetail detail) {
        JsonUtil json = new JsonUtil();
        json.setStatus(400);
        Article article = detail.getArticle();
        if (article == null) {
            json.setMsg("数据获取失败");
            return json;
        }
        if (StringUtils.isEmpty(article.getTitle())) {
            json.setMsg("标题不能为空");
            return json;
        }
        if (StringUtils.isEmpty(article.getContent())) {
            json.setMsg("内容不能为空");
            return json;
        }
        if (detail.getCategory().isEmpty()) {
            json.setMsg("类别不能为空");
            return json;
        }
        if (detail.getTags().isEmpty()) {
            json.setMsg("标签不能为空");
            return json;
        }

        articleService.addArticle(detail);
        json.setStatus(200);
        json.setMsg("文章发表成功");
        return json;
    }

    @PostMapping("/article/update")
    public JsonUtil updateArticle(@RequestBody ArticleDetail detail) {
        JsonUtil json = new JsonUtil();
        Article article = detail.getArticle();
        json.setFlag(false);
        json.setStatus(400);
        if (StringUtils.isBlank(article.getId())) {
            json.setMsg("数据获取失败");
            return json;
        }
        if (StringUtils.isBlank(article.getTitle())) {
            json.setMsg("标题不能为空");
            return json;
        }
        if (StringUtils.isBlank(article.getContent())) {
            json.setMsg("内容不能为空");
            return json;
        }
        List<String> categoryIds = detail.getCategory();
        if (categoryIds.isEmpty()) {
            json.setMsg("类别不能为空");
            return json;
        }
        List<String> tags = detail.getTags();
        if (tags.isEmpty()) {
            json.setMsg("标签不能为空");
            return json;
        }
        articleService.updateArticle(article, categoryIds, tags);
        json.setStatus(200);
        json.setMsg("更新成功");
        return json;
    }

}
