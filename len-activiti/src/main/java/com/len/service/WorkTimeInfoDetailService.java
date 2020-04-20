package com.len.service;
import com.len.base.BaseService;
import com.len.entity.WorkTimeInfoDetail;

import java.util.List;
public interface WorkTimeInfoDetailService extends BaseService<WorkTimeInfoDetail, String>{

    public int insertWorkTimeInfo(WorkTimeInfoDetail workTimeInfoDetail);

    public int selectHasSubmitted(WorkTimeInfoDetail workTimeInfoDetail);

    public List<WorkTimeInfoDetail> selectBySendUserId(WorkTimeInfoDetail workTimeInfoDetail);

    public List<WorkTimeInfoDetail> selectByReceiveUserId(WorkTimeInfoDetail workTimeInfoDetail);

    // 更新工时信息状态
    public int updateInfoStatus(WorkTimeInfoDetail workTimeInfoDetail);

    public WorkTimeInfoDetail selectById(String id);

    public WorkTimeInfoDetail selectHasSub(WorkTimeInfoDetail workTimeInfoDetail);

    public int updateById(WorkTimeInfoDetail workTimeInfoDetail);

    public List<WorkTimeInfoDetail> selectSubmitted(WorkTimeInfoDetail workTimeInfoDetail);
}
