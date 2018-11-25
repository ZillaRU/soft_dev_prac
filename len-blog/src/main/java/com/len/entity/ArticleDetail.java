package com.len.entity;

import com.len.model.Article;
import com.len.model.SimpleArticle;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuxiaomeng
 * @date 2018/11/20.
 * @email 154040976@qq.com
 */
@Data
public class ArticleDetail {
    /**
     * 文章对象
     */
    Article article;
    /**
     * 文章标签
     */
    private List<String> tags = new ArrayList<>();

    /**
     * 文章类别
     */
    private List<String> category = new ArrayList<>();

    /**
     * 上一篇
     */
    private SimpleArticle previous;

    /**
     * 下一篇
     */
    private SimpleArticle next;

}
