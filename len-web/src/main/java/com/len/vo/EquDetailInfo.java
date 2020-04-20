package com.len.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


//添加设备-前端传回
@Data
public class EquDetailInfo {

    private String rId;

    private String pName;

    private String rManager;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rDate;

    private String rState;

    private String rentState;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
