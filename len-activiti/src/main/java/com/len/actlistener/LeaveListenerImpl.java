package com.len.actlistener;

import com.len.entity.UserLeave;
import java.util.Map;
import org.activiti.engine.delegate.DelegateTask;

/**
 * @author zhuxiaomeng
 * @date 2018/1/25.
 * @email 154040976@qq.com
 *
 * 自定义请假流程 监听器
 */
public class LeaveListenerImpl extends ActNodeListener {

  @Override
  public void notify(DelegateTask delegateTask) {
    super.notify(delegateTask);
    String taskId=delegateTask.getId();
    System.out.println(taskId);
    Map<String,Object> map =delegateTask.getVariables();
    UserLeave userLeave= (UserLeave) map.get("userLeave");
    delegateTask.addCandidateUser(userLeave.getUserId());
    System.out.println(userLeave);
  }
}
