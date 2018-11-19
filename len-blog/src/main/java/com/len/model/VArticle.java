package com.len.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuxiaomeng
 * @date 2018/10/14.
 * @email 154040976@qq.com
 */
@Data
public class VArticle {

    private String title;

    private String content;

    private List<String> category=new ArrayList<>();

    private List<String> tags=new ArrayList<>();
}
