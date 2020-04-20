package com.len.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Data
@Getter
@Setter
public class ProWorInfoTmp {
    private String proId;

    private String proName;

    private String pmId;

    private String pmName;

    private String proStatus;

    private String dev;

    private String devLeader;

    private String test;

    private String testLeader;

    private String confMan;

    private String qa;

    private String epg;

    public void setProId(String proId){
        this.proId = proId;
    }

    public void setProName(String proName){
        this.proName = proName;
    }

    public void setPmId(String pmId){
        this.pmId = pmId;
    }

    public void setPmName(String pmName){
        this.pmName = pmName;
    }

    public void setProStatus(String proStatus){
        this.proStatus = proStatus;
    }

    public void setDev(String dev){
        this.dev = dev;
    }

    public void setDevLeader(String devLeader){
        this.devLeader = devLeader;
    }

    public void setTest(String test){
        this.test = test;
    }

    public void setTestLeader(String testLeader){
        this.testLeader = testLeader;
    }

    public void setConfMan(String confMan){
        this.confMan = confMan;
    }

    public void setQa(String qa){
        this.qa = qa;
    }

    public void setEpg(String epg){
        this.epg = epg;
    }

}
