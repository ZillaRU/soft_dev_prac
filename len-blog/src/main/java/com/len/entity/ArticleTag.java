package com.len.entity;

import javax.persistence.*;

@Table(name = "blog_article_tag")
public class ArticleTag {
    @Id
    @Column(name = "article_id")
    private String articleId;

    @Column(name = "tag_id")
    private String tagId;

    /**
     * @return article_id
     */
    public String getArticleId() {
        return articleId;
    }

    /**
     * @param articleId
     */
    public void setArticleId(String articleId) {
        this.articleId = articleId == null ? null : articleId.trim();
    }

    /**
     * @return tag_id
     */
    public String getTagId() {
        return tagId;
    }

    /**
     * @param tagId
     */
    public void setTagId(String tagId) {
        this.tagId = tagId == null ? null : tagId.trim();
    }
}