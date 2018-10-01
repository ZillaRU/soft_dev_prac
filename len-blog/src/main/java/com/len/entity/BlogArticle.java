package com.len.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "blog_article")
public class BlogArticle {
    @Id
    @GeneratedValue(generator = "JDBC")
    private String id;

    /**
     * code
     */
    private String code;

    /**
     * 类别id
     */
    @Column(name = "category_id")
    private String categoryId;

    /**
     * 标题
     */
    private String title;

    /**
     * 阅读次数
     */
    @Column(name = "read_number")
    private Integer readNumber;

    /**
     * 次序(置顶功能)
     */
    @Column(name = "top_num")
    private Integer topNum;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 文章内容
     */
    private String content;

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
     * 获取code
     *
     * @return code - code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置code
     *
     * @param code code
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取类别id
     *
     * @return category_id - 类别id
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * 设置类别id
     *
     * @param categoryId 类别id
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取阅读次数
     *
     * @return read_number - 阅读次数
     */
    public Integer getReadNumber() {
        return readNumber;
    }

    /**
     * 设置阅读次数
     *
     * @param readNumber 阅读次数
     */
    public void setReadNumber(Integer readNumber) {
        this.readNumber = readNumber;
    }

    /**
     * 获取次序(置顶功能)
     *
     * @return top_num - 次序(置顶功能)
     */
    public Integer getTopNum() {
        return topNum;
    }

    /**
     * 设置次序(置顶功能)
     *
     * @param topNum 次序(置顶功能)
     */
    public void setTopNum(Integer topNum) {
        this.topNum = topNum;
    }

    /**
     * @return create_by
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * @param createBy
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * @return update_by
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * @param updateBy
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return update_date
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取文章内容
     *
     * @return content - 文章内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置文章内容
     *
     * @param content 文章内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}