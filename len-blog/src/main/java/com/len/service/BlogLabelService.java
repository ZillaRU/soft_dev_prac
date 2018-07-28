package com.len.service;

import com.len.base.BaseService;
import com.len.entity.BlogLabel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhuxiaomeng
 * @date 2018/7/28.
 * @email 154040976@qq.com
 */
public interface BlogLabelService extends BaseService<BlogLabel, String> {

    @Override
    public int insertList(@Param("list") List<BlogLabel> blogLabels);
}
