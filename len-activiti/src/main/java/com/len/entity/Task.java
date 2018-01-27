package com.len.entity;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zhuxiaomeng
 * @date 2018/1/21.
 * @email 154040976@qq.com
 *
 * 流程任务
 */

@Getter
@Setter
@ToString
public class Task {
  private String id;
  private String name;
  private Date createTime;
  private String assignee;
  private String processInstanceId;//流程实例id
  private String processDefinitionId;//流程定义id
  private String description;
  private String category;

  private String userName;
  private String reason;

  public Task() {
  }
  public Task(org.activiti.engine.task.Task t) {
    this.id=t.getId();
    this.name=t.getName();
    this.createTime=t.getCreateTime();
    this.assignee=t.getAssignee();
    this.processInstanceId=t.getProcessInstanceId();
    this.processDefinitionId=t.getProcessDefinitionId();
    this.description=t.getDescription();
    this.category=t.getCategory();
  }
}
