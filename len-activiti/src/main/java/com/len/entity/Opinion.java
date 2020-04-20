package com.len.entity;

import java.util.Date;

public class Opinion {
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
