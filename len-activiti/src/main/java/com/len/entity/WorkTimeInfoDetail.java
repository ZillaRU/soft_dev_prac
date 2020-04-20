package com.len.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "work_time_info_detail")
@ToString
@Data
@Getter
@Setter

public class WorkTimeInfoDetail {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "submit_date")
    private String submitDate;

    @Column(name = "func_id")
    private String funcId;

    @Column(name = "func_name")
    private String funcName;

    @Column(name = "activ_id")
    private String activId;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "send_user_id")
    private String sendUserId;

    @Column(name = "send_user_name")
    private String sendUserName;

    @Column(name = "receive_user_id")
    private String receiveUserId;

    @Column(name = "receive_user_name")
    private String receiveUserName;

    @Column(name = "note")
    private String note;

    @Column(name = "info_status")
    private String infoStatus;

    @Column(name = "pro_id")
    private String proId;

    @Column(name = "pro_name")
    private String proName;

    @Column(name = "pm_name")
    private String pmName;

    public void setId(String id) {
        this.id = id;
    }

    public void setSubmitDate(String submitDate){
        this.submitDate = submitDate;
    }

    public void setFuncId(String funcId) {
        this.funcId = funcId;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public void setActivId(String activId) {
        this.activId = activId;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    public void setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName;
    }

    public void setReceiveUserId(String receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public void setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setInfoStatus(String infoStatus) {
        this.infoStatus = infoStatus;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public void setPmName(String pmName) {
        this.pmName = pmName;
    }

    public String getId() {
        return id;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public String getFuncId() {
        return funcId;
    }

    public String getFuncName() {
        return funcName;
    }

    public String getActivId() {
        return activId;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getSendUserId() {
        return sendUserId;
    }

    public String getSendUserName() {
        return sendUserName;
    }

    public String getReceiveUserId() {
        return receiveUserId;
    }

    public String getReceiveUserName() {
        return receiveUserName;
    }

    public String getNote() {
        return note;
    }

    public String getInfoStatus() {
        return infoStatus;
    }

    public String getProId() {
        return proId;
    }

    public String getProName() {
        return proName;
    }

    public String getPmName() {
        return pmName;
    }
}
