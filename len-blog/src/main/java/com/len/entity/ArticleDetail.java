package com.len.entity;

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
    BlogArticle article;
    /**
     * 文章标签
     */
    private List<String> tags = new ArrayList<>();

    /**
     * 文章类别
     */
    private List<String> category = new ArrayList<>();

}
