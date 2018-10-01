package com.len.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author zhuxiaomeng
 * @date 2018/10/1.
 * @email 154040976@qq.com
 */
@Data
public class ArticleList {

    private String id;

    private String code;

    private String title;

    private Integer topNum;

    private String createBy;

    private String createDate;

    private String content;

    public ArticleList() {
    }

    public ArticleList(String id, String code, String title, Integer topNum, String createBy, String createDate,String content) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.topNum = topNum;
        this.createBy = createBy;
        this.createDate = createDate;
        this.content=content;
    }
}
