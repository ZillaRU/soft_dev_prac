package com.len.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Table(name = "equ_info")
public class EquInfo {

    @Id
    @Column(name = "R_ID")
    private String rId;

    @Column(name = "P_Name")
    private String pName;

    @Column(name = "R_Manager")
    private String rManager;

    @Column(name = "R_Date")
    private Date rDate;

    @Column(name = "R_State")
    private String rState;

    @Column(name = "Rent_State")
    private String rentState;

    @Column(name = "R_Finish")
    private Date rFinish;

    public EquInfo() {
    }

    public EquInfo(String rId, String pName, String rManager, Date rDate, String rState, String rentState, Date rFinish) {
        this.rId = rId;
        this.pName = pName;
        this.rManager = rManager;
        this.rDate = rDate;
        this.rState = rState;
        this.rentState = rentState;
        this.rFinish = rFinish;
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getrManager() {
        return rManager;
    }

    public void setrManager(String rManager) {
        this.rManager = rManager;
    }

    public Date getrDate() {
        return rDate;
    }

    public void setrDate(Date rDate) {
        this.rDate = rDate;
    }

    public String getrState() {
        return rState;
    }

    public void setrState(String rState) {
        this.rState = rState;
    }

    public String getRentState() {
        return rentState;
    }

    public void setRentState(String rentState) {
        this.rentState = rentState;
    }

    public Date getrFinish() {
        return rFinish;
    }

    public void setrFinish(Date rFinish) {
        this.rFinish = rFinish;
    }
}