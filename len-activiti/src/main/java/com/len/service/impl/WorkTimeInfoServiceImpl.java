package com.len.service.impl;

import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.ProWorInfoMan;
import com.len.entity.WorkTimeInfo;
import com.len.mapper.WorkTimeInfoMapper;
import com.len.service.WorkTimeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<WorkTimeInfo> selectByProIdUserId(WorkTimeInfo workTimeInfo) {
        return workTimeInfoMapper.selectByProIdUserId(workTimeInfo);
    }

    public List<WorkTimeInfo> selectByUserId(WorkTimeInfo workTimeInfo) {
        return workTimeInfoMapper.selectByUserId(workTimeInfo);
    }

    public WorkTimeInfo selectById(String id){
        return workTimeInfoMapper.selectById(id);
    }
}
