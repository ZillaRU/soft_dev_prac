package com.len.service.impl;

import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.BlogMenu;
import com.len.mapper.BlogMenuMapper;
import com.len.service.BlogMenuService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhuxiaomeng
 * @date 2018/7/22.
 * @email 154040976@qq.com
 */
public class BlogMenuServiceImpl extends BaseServiceImpl<BlogMenu, String> implements BlogMenuService {

    @Autowired
    private BlogMenuMapper blogMenuMapper;

    @Override
    public BaseMapper<BlogMenu, String> getMappser() {
        return blogMenuMapper;
    }
}
