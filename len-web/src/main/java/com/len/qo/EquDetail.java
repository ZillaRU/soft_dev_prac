package com.len.qo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EquDetail {

    private String rId;

    private String pName;

    private String rManager;

    private Date rDate;

    private String rState;

    private String rentState;

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
