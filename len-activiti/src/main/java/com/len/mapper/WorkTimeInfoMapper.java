package com.len.mapper;

import com.len.base.BaseMapper;
import com.len.entity.WorkTimeInfo;

import java.util.List;

public interface WorkTimeInfoMapper extends BaseMapper<WorkTimeInfo, String> {
    //初始化时,删除之前的存储
    public int delByProId(String proId);

    //插入工时信息
    public int insertWorkTimeInfo(WorkTimeInfo workTimeInfo);

    public WorkTimeInfo selectById(String id);

    //排除重复的发送方接收方
    public List<WorkTimeInfo> selectByProIdUserId(WorkTimeInfo workTimeInfo);

    //排除重复的发送方接收方
    public List<WorkTimeInfo> selectByUserId(WorkTimeInfo workTimeInfo);
}
