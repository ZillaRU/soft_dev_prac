package com.len.qo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

//查看风险信息-传回前端
@AllArgsConstructor
@ToString
@Data
public class RskDetail {

    private String hId;

    private String pId;

    private String pName;

    private String hType;

    private String hDes;

    private String hGrade;

    private String hInfluence;

    private String hTactics;

    private String hState;

    private int hFrequency;

    private String hManager;

    private String[] hMember;

    private String hCreator;

//    private List<String> pMember;
}
