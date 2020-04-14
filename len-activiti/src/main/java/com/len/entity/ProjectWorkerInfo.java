package com.len.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;

import java.util.List;


@Table(name = "pro_worker_info")
@ToString
@Data
@Getter
@Setter
public class ProjectWorkerInfo {
    @Id
    @Column(name = "id")
    private String proId;

    @Column(name = "pro_name")
    private String proName;

    @Column(name = "pro_status")
    private String proStatus;

    @Column(name = "pm_id")
    private String pmId;

    @Column(name = "pm_name")
    private String pmName;

    @Column(name = "dev_leader_id")
    private String devLeaderId;

    @Column(name = "dev_leader_name")
    private String devLeaderName;

    @Column(name = "test_leader_id")
    private String testLeaderId;

    @Column(name = "test_leader_name")
    private String testLeaderName;

    @Column(name = "config_manager_id")
    private String configManagerId;

    @Column(name = "config_manager_name")
    private String configManagerName;

    @Column(name = "qa_manager_id")
    private String qaManagerId;

    @Column(name = "qa_manager_name")
    private String qaManagerName;

    @Column(name = "epg_leader_id")
    private String epgLeaderId;

    @Column(name = "epg_leader_name")
    private String epgLeaderName;

    public void setPmId(String pmId) {
        this.pmId = pmId;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProId(){ return this.proId;}

    private List<ProWorInfoMan> worInfoList = new ArrayList<>();

    public void addWorInfoList(ProWorInfoMan proWorInfoMan){
        this.worInfoList.add(proWorInfoMan);
    }

    public List<ProWorInfoMan> getWorInfoList() {
        return worInfoList;
    }

    public void setWorInfoList(List<ProWorInfoMan> proWorInfoMan) {
        this.worInfoList = proWorInfoMan;
    }

}
