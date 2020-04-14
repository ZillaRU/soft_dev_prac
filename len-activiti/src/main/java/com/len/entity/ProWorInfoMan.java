package com.len.entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Table(name = "pro_dev_group")
@ToString
@Data
@Getter
@Setter
public class ProWorInfoMan {
    @Id
    @Column(name = "id")
    private String id;

    public String roleMapper[] = new String[]{"开发负责人",
    "开发人员","测试负责人","测试人员","配置管理人员","QA管理人员","epg人员"};

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "pro_id")
    private String proId;

    @Column(name = "pro_name")
    private String proName;

    @Column(name = "pm_id")
    private String pmId;

    @Column(name = "pro_role_id")
    private int proRoleId;

    private String proRoleName;

    public void setProRoleName(int proRoleId){
        this.proRoleName = this.roleMapper[proRoleId];
    }

    public int getProRoleId(){
        return this.proRoleId;
    }

    public String getProId(){
        return this.proId;
    }

    public void setPmId(String pmId){this.pmId = pmId;}

    public void setProName(String proName){this.proName = proName;}

    public void setId(String id){this.id = id;}

    public void setProId(String proId){this.id = proId;}

    public void setUserId(String userId){this.userId = userId;}

    public void setProRoleId(int proRoleId){this.proRoleId = proRoleId;}

    public void setUserName(String userName){this.userName = userName;}

    public void setUserEmail(String userEmail){this.userEmail = userEmail;}

    public void setUserPhone(String userPhone){this.userPhone = userPhone;}
}
