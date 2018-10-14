package com.len.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "blog_article_category")
public class ArticleCategory {
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 文章id
     */
    @Column(name = "article_id")
    private String articleId;

    /**
     * 标签id
     */
    @Column(name = "category_id")
    private String categoryId;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取文章id
     *
     * @return article_id - 文章id
     */
    public String getArticleId() {
        return articleId;
    }

    /**
     * 设置文章id
     *
     * @param articleId 文章id
     */
    public void setArticleId(String articleId) {
        this.articleId = articleId == null ? null : articleId.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}