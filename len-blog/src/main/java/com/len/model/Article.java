package com.len.model;

import lombok.Data;

import java.util.Date;

@Data
public class Article {

    private String id;

    /**
     * code
     */
    private String code;


    /**
     * 标题
     */
    private String title;


    private String firstImg;

    /**
     * 阅读次数
     */
    private Integer readNumber;

    /**
     * 次序(置顶功能)
     */
    private Integer topNum;

    private String createBy;

    private String createName;

    private String updateBy;

    private Date createDate;

    private Date updateDate;

    /**
     * 文章内容
     */
    private String content;

    private Byte delFlag;


}