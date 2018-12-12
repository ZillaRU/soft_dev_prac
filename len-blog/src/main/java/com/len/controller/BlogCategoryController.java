package com.len.controller;

import com.len.entity.BlogCategory;
import com.len.service.BlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 类别
 *
 * @author zhuxiaomeng
 * @date 2018/9/9.
 * @email 154040976@qq.com
 */
@CrossOrigin
@RestController
@RequestMapping("/blog")
public class BlogCategoryController {

    @Autowired
    private BlogCategoryService categoryService;


    @GetMapping("/menu")
    public List<Map> menuList() {
        List<BlogCategory> categories = categoryService.selectAll();
        categories.sort(Comparator.comparing(BlogCategory::getSequence));
        List<Map> cates = new ArrayList<>();
        for (BlogCategory category : categories) {
            Map<String, String> cateMap = new HashMap<>(2);
            cateMap.put("name", category.getName());
            cateMap.put("code", category.getCode());
            cates.add(cateMap);
        }
        return cates;
    }
}
