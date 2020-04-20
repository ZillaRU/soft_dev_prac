package com.len.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


@ToString
@Data
@Getter
@Setter
@Table(name="pro_worker_info")
public class ProjectWorkerInfo {
    @Id
    @Column(name = "pro_id")
    private String proId;

    @Column(name = "pro_name")
    private String proName;

    @Column(name = "pro_status")
    private String proStatus;

    @Column(name = "pm_id")
    private String pmId;

    @Column(name = "pm_name")
    private String pmName;

    public void setProStatus(String proStatus) {
        this.proStatus = proStatus;
    }

    public void setPmId(String pmId) {
        this.pmId = pmId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProStatus() {
        return this.proStatus;
    }

    public String getProId() {
        return this.proId;
    }

    public String getProName() {
        return this.proName;
    }

    public String getPmName() {
        return this.pmName;
    }

    public String getPmId() {
        return this.pmId;
    }

}
