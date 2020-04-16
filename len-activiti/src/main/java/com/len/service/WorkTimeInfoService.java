package com.len.service;

import com.len.base.BaseService;
import com.len.entity.WorkTimeInfo;

public interface WorkTimeInfoService extends BaseService<WorkTimeInfo, String> {
    //初始化时,删除之前的存储
    public int delByProId(String proId);

    //插入工时信息
    public int insertWorkTimeInfo(WorkTimeInfo workTimeInfo);
}
