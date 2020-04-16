package com.len.mapper;

import com.len.entity.WorkTimeInfo;
import com.len.base.BaseMapper;

public interface WorkTimeInfoMapper extends BaseMapper<WorkTimeInfo,String>{
    //初始化时,删除之前的存储
    public int delByProId(String proId);

    //插入工时信息
    public int insertWorkTimeInfo(WorkTimeInfo workTimeInfo);
}
