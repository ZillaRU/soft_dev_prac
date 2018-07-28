package com.len.controller;

import com.alibaba.fastjson.JSON;
import com.len.base.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuxiaomeng
 * @date 2018/7/22.
 * @email 154040976@qq.com
 */
@RestController
@RequestMapping("/blog")
public class BlogMenuController extends BaseController {

    @GetMapping("/menu")
    public List<String> menuList(){
        List<String> list=new ArrayList<>();
        list.add("java");
        list.add("架构");
        list.add("Linux");
        list.add("其他");
        System.out.println(JSON.toJSON(list));
        return list;
    }
}
