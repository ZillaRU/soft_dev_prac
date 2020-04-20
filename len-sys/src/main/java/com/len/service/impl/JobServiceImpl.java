package com.len.service.impl;

import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.core.quartz.JobTask;
import com.len.entity.SysJob;
import com.len.exception.MyException;
import com.len.mapper.SysJobMapper;
import com.len.service.JobService;
import com.len.util.BeanUtil;
import com.len.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhuxiaomeng
 * @date 2018/1/6.
 * @email 154040976@qq.com
 */
@Service
@Slf4j
public class JobServiceImpl extends BaseServiceImpl<SysJob, String> implements JobService {

    @Autowired
    SysJobMapper jobMapper;

    @Autowired
    JobTask jobTask;

    @Autowired
    JobService jobService;

    @Override
    public BaseMapper<SysJob, String> getMappser() {
        return jobMapper;
    }

    @Override
    public boolean updateJob(SysJob job) {
        try {
            SysJob oldJob = selectByPrimaryKey(job.getId());
            BeanUtil.copyNotNullBean(job, oldJob);
            updateByPrimaryKey(oldJob);
            return true;
        } catch (MyException e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public JsonUtil del(String id) {
        JsonUtil j = new JsonUtil();
        j.setFlag(false);
        if (StringUtils.isEmpty(id)) {
            j.setMsg("获取数据失败");
            return j;
        }
        try {
            SysJob job = selectByPrimaryKey(id);
            boolean flag = jobTask.checkJob(job);
            if ((flag && !job.getStatus()) || !flag && job.getStatus()) {
                j.setMsg("您任务表状态和web任务状态不一致,无法删除");
                return j;
            }
            if (flag) {
                j.setMsg("该任务处于启动中，无法删除");
                return j;
            }
            jobService.deleteByPrimaryKey(id);
            j.setFlag(true);
            j.setMsg("任务删除成功");
        } catch (MyException e) {
            j.setMsg("任务删除异常");
            e.printStackTrace();
        }
        return j;
    }

    @Override
    public boolean startJob(String id) {
        try {
            SysJob job = jobService.selectByPrimaryKey(id);
            jobTask.startJob(job);
            job.setStatus(true);
            jobService.updateByPrimaryKey(job);
            return true;
        } catch (MyException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean stopJob(String id) {
        try {
            SysJob job = jobService.selectByPrimaryKey(id);
            jobTask.remove(job);
            job.setStatus(false);
            jobService.updateByPrimaryKey(job);
            return true;
        } catch (MyException e) {
            e.printStackTrace();
        }
        return false;
    }
}
