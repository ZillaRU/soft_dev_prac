package com.len.service.impl;

import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.BlogArticle;
import com.len.mapper.BlogArticleMapper;
import com.len.service.BlogArticleService;
import com.len.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * @author zhuxiaomeng
 * @date 2018/9/9.
 * @email 154040976@qq.com
 */
@Service
public class BlogArticleServiceImpl extends BaseServiceImpl<BlogArticle, String> implements BlogArticleService {

    @Autowired
    private BlogArticleMapper blogArticleMapper;

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
        json.setData(articles.get(0));
        json.setFlag(true);
        return json;
    }
}
