package com.len.mapper;

import com.len.base.BaseMapper;
import com.len.entity.SysJob;
import com.len.entity.SysLog;
import java.util.List;

public interface SysLogMapper extends BaseMapper<SysLog, String> {
    int deleteByPrimaryKey(Integer id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);

    List<SysLog> selectListByPage(SysLog sysLog);
}