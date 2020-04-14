package com.len.mapper;

import com.len.base.BaseMapper;
import com.len.entity.ProWorInfoMan;

import java.util.List;

public interface ProWorInfoManMapper extends BaseMapper<ProWorInfoMan,String> {

    //按照项目id搜索出该项目下的开发人员
    List<ProWorInfoMan> selectByPmId(String pmId);

    // 支持项目名称的模糊搜索
    List<ProWorInfoMan> selectByProName(ProWorInfoMan worInfo);

    //按照id删除某项目下的某成员
    public int delById(String Id);

    //插入管理人员
    public int insertProWor(ProWorInfoMan worInfo);
}
