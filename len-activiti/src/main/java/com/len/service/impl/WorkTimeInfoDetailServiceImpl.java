package com.len.service.impl;

import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.WorkTimeInfoDetail;
import com.len.mapper.WorkTimeInfoDetailMapper;
import com.len.service.WorkTimeInfoDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class WorkTimeInfoDetailServiceImpl extends BaseServiceImpl<WorkTimeInfoDetail, String> implements WorkTimeInfoDetailService {
    @Autowired
    private WorkTimeInfoDetailMapper workTimeInfoDetailMapper;

    @Override
    public BaseMapper<WorkTimeInfoDetail, String> getMappser() {
        return workTimeInfoDetailMapper;
    }

    public int insertWorkTimeInfo(WorkTimeInfoDetail workTimeInfoDetail) {
        return workTimeInfoDetailMapper.insertWorkTimeInfo(workTimeInfoDetail);
    }

    public int selectHasSubmitted(WorkTimeInfoDetail workTimeInfoDetail) {
        return workTimeInfoDetailMapper.selectHasSubmitted(workTimeInfoDetail);
    }

    public List<WorkTimeInfoDetail> selectBySendUserId(WorkTimeInfoDetail workTimeInfoDetail) {
        return workTimeInfoDetailMapper.selectBySendUserId(workTimeInfoDetail);
    }

    public List<WorkTimeInfoDetail> selectByReceiveUserId(WorkTimeInfoDetail workTimeInfoDetail) {
        return workTimeInfoDetailMapper.selectByReceiveUserId(workTimeInfoDetail);
    }

    // 更新工时信息状态
    public int updateInfoStatus(WorkTimeInfoDetail workTimeInfoDetail) {
        return workTimeInfoDetailMapper.updateInfoStatus(workTimeInfoDetail);
    }

    public WorkTimeInfoDetail selectById(String id) {
        return workTimeInfoDetailMapper.selectById(id);
    }

    public WorkTimeInfoDetail selectHasSub(WorkTimeInfoDetail workTimeInfoDetail) {
        return workTimeInfoDetailMapper.selectHasSub(workTimeInfoDetail);
    }

    public int updateById(WorkTimeInfoDetail workTimeInfoDetail) {
        return workTimeInfoDetailMapper.updateById(workTimeInfoDetail);
    }

    public List<WorkTimeInfoDetail> selectSubmitted(WorkTimeInfoDetail workTimeInfoDetail) {
        return workTimeInfoDetailMapper.selectSubmitted(workTimeInfoDetail);
    }

}
