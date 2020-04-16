package com.len.service.impl;

import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.ProWorInfoMan;
import com.len.entity.WorkTimeInfo;
import com.len.mapper.WorkTimeInfoMapper;
import com.len.service.WorkTimeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkTimeInfoServiceImpl extends BaseServiceImpl<WorkTimeInfo, String> implements WorkTimeInfoService {

    @Autowired
    private WorkTimeInfoMapper workTimeInfoMapper;

    @Override
    public BaseMapper<WorkTimeInfo, String> getMappser() { return workTimeInfoMapper; }

    public int delByProId(String proId) {
        return workTimeInfoMapper.delByProId(proId);
    }

    public int insertWorkTimeInfo(WorkTimeInfo workTimeInfo){
        return workTimeInfoMapper.insertWorkTimeInfo(workTimeInfo);
    }
}
