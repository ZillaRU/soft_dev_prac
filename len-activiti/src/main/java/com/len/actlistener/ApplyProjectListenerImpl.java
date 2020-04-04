package com.len.actlistener;

import com.len.entity.SysUser;
import com.len.service.SysUserService;
import com.len.util.CommonUtil;
import com.len.util.SpringUtil;
import org.activiti.engine.delegate.DelegateTask;

public class ApplyProjectListenerImpl extends ActNodeListener {

    public void notify(DelegateTask delegateTask) {
        SysUser me = SpringUtil.getBean(SysUserService.class).selectByPrimaryKey(CommonUtil.getUser().getId());
        String chief_id = me.getChiefId();
        System.out.println("\nApplyProjectListenerImpl is working + chief --> \n" + chief_id);
        // 获取节点 并设定assignee 还是在上一步完成后设定吧= =
        // SpringUtil.getBean(TaskService.class).setAssignee(taskId, chief_id);
        super.notify(delegateTask);
    }
}
