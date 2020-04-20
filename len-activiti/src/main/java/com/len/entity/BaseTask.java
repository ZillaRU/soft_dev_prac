package com.len.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by kingdee-001 on 2018/6/30.
 */
@MappedSuperclass
public abstract class BaseTask implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "JDBC")
    protected String id;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    @Column(name = "user_id")
    protected String userId;

    @Column(name = "user_name")
    protected String userName;

    @Column(name = "process_instance_Id")
    protected String processInstanceId;

    protected String status;

    @Column(name = "create_date")
    protected Date createDate;

    @Column(name = "create_by")
    protected String createBy;

    @Column(name = "update_date")
    protected Date updateDate;

    @Column(name = "update_by")
    protected String updateBy;

    @Column(name = "proj_name")
    protected String projName;

    //***实时节点信息
    @Transient
    @Column(name = "task_name")
    protected String taskName;

    private String urlpath;

    private Integer submittimes;

    /**
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * @return process_instance_Id
     */
    public String getProcessInstanceId() {
        return processInstanceId;
    }

    /**
     * @param processInstanceId
     */
    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId == null ? null : processInstanceId.trim();
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return create_by
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * @param createBy
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * @return update_date
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return update_by
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * @param updateBy
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getUrlpath() {
        return urlpath;
    }

    public void setUrlpath(String urlpath) {
        this.urlpath = urlpath;
    }

    public Integer getSubmittimes() {
        return submittimes;
    }

    public void setSubmittimes(Integer submittimes) {
        this.submittimes = submittimes;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String name) {
        this.projName = name;
    }
}
