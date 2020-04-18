package com.len.entity;

import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@Table(name = "rsk_info")
public class RskInfo {
    @Id
    @Column(name = "H_ID")
    private String hId;

    @Column(name = "P_ID")
    private String pId;

    @Column(name = "H_Type")
    private String hType;

    @Column(name = "H_Des")
    private String hDes;

    @Column(name = "H_Grade")
    private String hGrade;

    @Column(name = "H_Influence")
    private String hInfluence;

    @Column(name = "H_Tactics")
    private String hTactics;

    @Column(name = "H_State")
    private String hState;

    @Column(name = "H_Frequency")
    private Integer hFrequency;

    @Column(name = "H_Manager")
    private String hManager;

    @Column(name = "H_Creator")
    private String hCreator;

    /**
     * @return H_ID
     */
    public String gethId() {
        return hId;
    }

    /**
     * @param hId
     */
    public void sethId(String hId) {
        this.hId = hId == null ? null : hId.trim();
    }

    /**
     * @return P_ID
     */
    public String getpId() {
        return pId;
    }

    /**
     * @param pId
     */
    public void setpId(String pId) {
        this.pId = pId == null ? null : pId.trim();
    }

    /**
     * @return H_Type
     */
    public String gethType() {
        return hType;
    }

    /**
     * @param hType
     */
    public void sethType(String hType) {
        this.hType = hType == null ? null : hType.trim();
    }

    /**
     * @return H_Des
     */
    public String gethDes() {
        return hDes;
    }

    /**
     * @param hDes
     */
    public void sethDes(String hDes) {
        this.hDes = hDes == null ? null : hDes.trim();
    }

    /**
     * @return H_Grade
     */
    public String gethGrade() {
        return hGrade;
    }

    /**
     * @param hGrade
     */
    public void sethGrade(String hGrade) {
        this.hGrade = hGrade == null ? null : hGrade.trim();
    }

    /**
     * @return H_Influence
     */
    public String gethInfluence() {
        return hInfluence;
    }

    /**
     * @param hInfluence
     */
    public void sethInfluence(String hInfluence) {
        this.hInfluence = hInfluence == null ? null : hInfluence.trim();
    }

    /**
     * @return H_Tactics
     */
    public String gethTactics() {
        return hTactics;
    }

    /**
     * @param hTactics
     */
    public void sethTactics(String hTactics) {
        this.hTactics = hTactics == null ? null : hTactics.trim();
    }

    /**
     * @return H_State
     */
    public String gethState() {
        return hState;
    }

    /**
     * @param hState
     */
    public void sethState(String hState) {
        this.hState = hState == null ? null : hState.trim();
    }

    /**
     * @return H_Frequency
     */
    public Integer gethFrequency() {
        return hFrequency;
    }

    /**
     * @param hFrequency
     */
    public void sethFrequency(Integer hFrequency) {
        this.hFrequency = hFrequency;
    }

    /**
     * @return H_Manager
     */
    public String gethManager() {
        return hManager;
    }

    /**
     * @param hManager
     */
    public void sethManager(String hManager) {
        this.hManager = hManager == null ? null : hManager.trim();
    }

    /**
     * @return H_Creator
     */
    public String gethCreator() {
        return hCreator;
    }

    /**
     * @param hCreator
     */
    public void sethCreator(String hCreator) {
        this.hCreator = hCreator == null ? null : hCreator.trim();
    }
}