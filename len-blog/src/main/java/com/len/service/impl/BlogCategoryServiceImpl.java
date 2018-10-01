package com.len.service.impl;

import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.BlogCategory;
import com.len.mapper.BlogCategoryMapper;
import com.len.service.BlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhuxiaomeng
 * @date 2018/7/22.
 * @email 154040976@qq.com
 */
@Service
public class BlogCategoryServiceImpl extends BaseServiceImpl<BlogCategory, String> implements BlogCategoryService {

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;

    @Override
    public BaseMapper<BlogCategory, String> getMappser() {
        return blogCategoryMapper;
    }
}
