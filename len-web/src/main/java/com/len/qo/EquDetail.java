package com.len.qo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class EquDetail {

    private String rId;

    private String pName;

    private String rManager;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rDate;

    private String rState;

    private String rentState;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rFinish;

    public EquDetail(String rId, String pName, String rManager, Date rDate, String rState, String rentState, Date rFinish) {
        this.rId = rId;
        this.pName = pName;
        this.rManager = rManager;
        this.rDate = rDate;
        this.rState = rState;
        this.rentState = rentState;
        this.rFinish = rFinish;
    }

    //    private List<String> pMember;
}
