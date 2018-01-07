package com.len.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SysJob implements Serializable {
    private String id;

    private String jobName;

    private String cron;

    private Boolean status;

    private String clazzPath;

    private String jobDesc;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private static final long serialVersionUID = 1L;
}