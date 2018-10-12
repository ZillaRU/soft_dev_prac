package com.len.controller;

import com.len.entity.BlogArticle;
import com.len.entity.BlogTag;
import com.len.service.BlogArticleService;
import com.len.service.BlogTagService;
import com.len.util.JsonUtil;
import com.len.util.ReType;
import com.len.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import tk.mybatis.mapper.entity.Condition;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @Value("${lenosp.imagePath}")
    private String imagePath;

    @Autowired
    private  UploadUtil uploadUtil;


    @Autowired
    private BlogTagService tagService;

    @GetMapping("/article/getList")
    public ReType getArticleList(BlogArticle article, Integer page, Integer limit) {
        BlogTag tag=new BlogTag();
        tag.setId(UUID.randomUUID().toString().replace("-", ""));
        tag.setTagCode("123");
        tag.setTagName("123");
        List<BlogTag> tags=new ArrayList<>();
        tags.add(tag);
        tagService.insertList(tags);
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

    @PostMapping("/article/addImage")
    public JsonUtil addImage(MultipartHttpServletRequest request) {
        MultipartFile multipartFile = request.getFile("file");
        String path = uploadUtil.upload(multipartFile);
        JsonUtil json = new JsonUtil();
        StringBuffer requestURL = request.getRequestURL();
        int serverPort = request.getServerPort();
        int i = requestURL.indexOf(String.valueOf(serverPort));
       String url= requestURL.substring(0,i);
        json.setData(url+String.valueOf(serverPort)+"/img/"+path);
        json.setFlag(true);
        return json;
    }
}
