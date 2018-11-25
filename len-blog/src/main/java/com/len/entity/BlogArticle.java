package com.len.entity;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "blog_article")
@Data
public class BlogArticle {
    @Id
    @Column(name = "id")
    private String id;

    /**
     * code
     */
    private String code;


    /**
     * 标题
     */
    private String title;

    /**
     * 列表缩略图
     */
    @Column(name="first_img")
    private String firstImg;

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

    @Column(name = "del_flag")
    private Byte delFlag;


}