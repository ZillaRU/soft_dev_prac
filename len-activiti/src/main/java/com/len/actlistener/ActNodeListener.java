package com.len.actlistener;

import com.len.entity.ActAssignee;
import com.len.service.ActAssigneeService;
import com.len.service.impl.ActAssigneeServiceImpl;
import com.len.util.AssigneeType;
import com.len.util.SpringUtil;

import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @author zhuxiaomeng
 * @date 2018/1/20.
 * @email 154040976@qq.com
 * <p>
 * 流程监听器 动态注入节点办理人
 */
public class ActNodeListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        //KEY
        String nodeId = delegateTask.getTaskDefinitionKey();
        ActAssigneeService actAssigneeService = SpringUtil.getBean(ActAssigneeServiceImpl.class);
        List<ActAssignee> assigneeList = actAssigneeService.selectListByPage(new ActAssignee(nodeId));
        for (ActAssignee assignee : assigneeList) {
            switch (assignee.getAssigneeType()) {
                case AssigneeType.GROUP_TYPE:
                    delegateTask.addCandidateGroup(assignee.getRoleId());
                    break;
                case AssigneeType.USER_TYPE:
                    delegateTask.addCandidateUser(assignee.getAssignee());
                    break;
            }
        }
    }
}
