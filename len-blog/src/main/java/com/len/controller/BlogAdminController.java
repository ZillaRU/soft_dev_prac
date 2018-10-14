package com.len.controller;

import com.len.entity.ArticleCategory;
import com.len.entity.BlogArticle;
import com.len.entity.BlogCategory;
import com.len.model.VArticle;
import com.len.service.ArticleCategoryService;
import com.len.service.BlogArticleService;
import com.len.service.BlogCategoryService;
import com.len.service.BlogTagService;
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

import java.util.*;

/**
 * @author zhuxiaomeng
 * @date 2018/10/1.
 * @email 154040976@qq.com
 * <p>
 * 博客后台业务管理
 */
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
    private BlogTagService tagService;

    @Autowired
    private BlogCategoryService categoryService;

    @Autowired
    private ArticleCategoryService articleCategoryService;

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
    public JsonUtil addArticle(VArticle article) {
        JsonUtil json = new JsonUtil();
        json.setStatus(400);
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
        if (article.getCategory().length == 0) {
            json.setMsg("类别不能为空");
            return json;
        }
        if (article.getTags().length == 0) {
            json.setMsg("标签不能为空");
            return json;
        }

        String articleId = UUID.randomUUID().toString().replace("-", "");
        BlogArticle blogArticle = new BlogArticle();
        blogArticle.setCode(generatorCode());
        blogArticle.setContent(article.getContent());
        blogArticle.setReadNumber(0);
        blogArticle.setTitle(article.getTitle());
        blogArticle.setTopNum(0);
        blogArticle.setId(articleId);
        blogArticle.setCreateDate(new Date());
        blogArticle.setCreateBy("zxm");
        articleService.insert(blogArticle);

        List<ArticleCategory> categories = new ArrayList<>();
        for (String cateId : article.getCategory()) {
            ArticleCategory articleCategory = new ArticleCategory();
            articleCategory.setId(UUID.randomUUID().toString().replace("-", ""));
            articleCategory.setArticleId(articleId);
            articleCategory.setCategoryId(cateId);
            categories.add(articleCategory);
        }
        articleCategoryService.insertList(categories);


        json.setStatus(200);
        json.setMsg("文章发表成功");
        return json;
    }

    private String generatorCode() {
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            result.append(random.nextInt(9) + 1);
        }
        BlogArticle article = new BlogArticle();
        article.setCode(result.toString());
        BlogArticle blogArticle = articleService.selectOne(article);
        if (blogArticle == null) {
            return result.toString();
        } else {
            return generatorCode();
        }
    }
}
