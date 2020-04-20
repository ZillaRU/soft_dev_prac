package com.len.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "pro_wor_info_man")
@ToString
@Data
@Getter
@Setter
public class ProWorInfoMan {
    @Id
    @Column(name = "id")
    private String id;

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

    @Column(name = "pro_role_name")
    private String proRoleName;

    public void setProRoleName(String proRoleName){
            this.proRoleName = proRoleName;
    }

    public String getProRoleName(){
        return this.proRoleName;
    }

    public String getProId(){
        return this.proId;
    }

    public String getUserName(){ return this.userName;}

    public void setPmId(String pmId){this.pmId = pmId;}

    public void setProName(String proName){this.proName = proName;}

    public void setId(String id){this.id = id;}

    public void setProId(String proId){this.proId = proId;}

    public void setUserId(String userId){this.userId = userId;}

    public void setUserName(String userName){this.userName = userName;}

    public void setUserEmail(String userEmail){this.userEmail = userEmail;}

    public void setUserPhone(String userPhone){this.userPhone = userPhone;}
}
