package com.len.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Table(name = "work_time_info")
@ToString
@Data
@Getter
@Setter

public class WorkTimeInfo {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "pro_id")
    private String proId;

    @Column(name = "pro_name")
    private String proName;

    @Column(name = "pm_id")
    private String pmId;

    @Column(name = "pm_name")
    private String pmName;

    @Column(name = "send_user_id")
    private String sendUserId;

    @Column(name = "send_user_name")
    private String sendUserName;

    @Column(name = "receive_user_id")
    private String receiveUserId;

    @Column(name = "receive_user_name")
    private String receiveUserName;

    public void setId(String id) {
        this.id = id;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public void setPmId(String pmId) { this.pmId = pmId;}

    public void setPmName(String pmName) {this.pmName = pmName;}

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

    public String getId() {
        return id;
    }

    public String getProId() {
        return proId;
    }

    public String getProName() {
        return proName;
    }

    public String getSendUserId() {
        return sendUserId;
    }

    public String getReceiveUserId() {
        return receiveUserId;
    }
}
