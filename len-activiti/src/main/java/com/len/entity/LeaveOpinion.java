package com.len.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuxiaomeng
 * @date 2018/1/26.
 * @email 154040976@qq.com
 *
 * 请假流程 审批信息
 */
@Getter
@Setter
public class LeaveOpinion implements Serializable{

  //审批人id
  private String opId;
  //审批人姓名
  private String opName;
  //审批意见
  private String opinion;
  //审批时间
  private Date createTime;
  //是否通过
  private boolean flag;
  //流程id
  private String taskId;

}
