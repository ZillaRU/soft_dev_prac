package com.len.model;

import lombok.Data;

/**
 * @author zhuxiaomeng
 * @date 2018/10/14.
 * @email 154040976@qq.com
 */
@Data
public class VArticle {

    private String title;

    private String content;

    private String[] category;

    private String[] tags;
}
