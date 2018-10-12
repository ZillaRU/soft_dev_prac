package com.len.entity;

import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;

@Table(name = "blog_tag")
public class BlogTag {
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 标签code
     */
    @Column(name = "tag_code")
    private String tagCode;

    /**
     * 标签name
     */
    @Column(name = "tag_name")
    private String tagName;

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
     * 获取标签code
     *
     * @return tag_code - 标签code
     */
    public String getTagCode() {
        return tagCode;
    }

    /**
     * 设置标签code
     *
     * @param tagCode 标签code
     */
    public void setTagCode(String tagCode) {
        this.tagCode = tagCode == null ? null : tagCode.trim();
    }

    /**
     * 获取标签name
     *
     * @return tag_name - 标签name
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * 设置标签name
     *
     * @param tagName 标签name
     */
    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }
}