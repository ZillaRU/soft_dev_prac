package com.len.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;

import java.util.List;


@ToString
@Data
@Getter
@Setter
@Table(name="pro_worker_info")
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

    public void setProStatus(String proStatus){
        this.proStatus = proStatus;
    }

    public void setPmId(String pmId) {
        this.pmId = pmId;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProStatus(){return this.proStatus;}

    public String getProId(){ return this.proId;}

    public String getProName(){return this.proName;}

    public String getPmName(){return this.pmName;}

    public String getPmId(){return this.pmId;}

}
