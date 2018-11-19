package com.len.service.impl;

import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.ArticleTag;
import com.len.mapper.ArticleTagMapper;
import com.len.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhuxiaomeng
 * @date 2018/11/19.
 * @email 154040976@qq.com
 */
@Service
public class ArticleTagServiceImpl extends BaseServiceImpl<ArticleTag, String> implements ArticleTagService {

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Override
    public BaseMapper<ArticleTag, String> getMappser() {
        return articleTagMapper;
    }
}
