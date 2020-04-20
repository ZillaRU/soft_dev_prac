package com.len.vo;

import lombok.Data;


//添加风险识别-前端传回
@Data
public class RskDetailInfo {

    private String hId;

    private String pId;

    private String hType;

    private String hDes;

    private String hGrade;

    private String hInfluence;

    private String hTactics;

    private String hState;

    private String hManager;

    private String[] hMember;
}
