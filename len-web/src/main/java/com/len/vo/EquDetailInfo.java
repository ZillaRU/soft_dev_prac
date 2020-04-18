package com.len.vo;

import lombok.Data;

import java.util.Date;


//添加设备-前端传回
@Data
public class EquDetailInfo {

    private String rId;

    private String pName;

    private String rManager;

    private Date rDate;

    private String rState;

    private String rentState;

    private Date rFinish;

    public String getRId() {
        return rId;
    }

    public Date getRDate() {
        return rDate;
    }

    public String getRState() {
        return rState;
    }

    public String getRentState() {
        return rentState;
    }

    public Date getRFinish() {
        return rFinish;
    }


}
