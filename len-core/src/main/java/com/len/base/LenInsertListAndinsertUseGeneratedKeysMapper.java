package com.len.base;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;

import java.util.List;

/**
 * @author zhuxiaomeng
 * @date 2018/10/12.
 * @email 154040976@qq.com
 */
@tk.mybatis.mapper.annotation.RegisterMapper
public interface LenInsertListAndinsertUseGeneratedKeysMapper<T> {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(type = MySpecialProvider.class, method = "dynamicSQL")
    int insertList(List<T> recordList);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(type = MySpecialProvider.class, method = "dynamicSQL")
    int insertUseGeneratedKeys(T record);
}
