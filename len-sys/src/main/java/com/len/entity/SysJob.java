package com.len.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_job")
@Data
@ToString
@EqualsAndHashCode
public class SysJob {
    @Id
    @GeneratedValue(generator = "JDBC")
    private String id;

    /**
     * 描述任务
     */
    @Column(name = "job_name")
    private String jobName;

    /**
     * 任务表达式
     */
    private String cron;

    /**
     * 状态:0未启动false/1启动true
     */
    private Boolean status;

    /**
     * 任务执行方法
     */
    @Column(name = "clazz_path")
    private String clazzPath;

    /**
     * 其他描述
     */
    @Column(name = "job_desc")
    private String jobDesc;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_date")
    private Date updateDate;
}