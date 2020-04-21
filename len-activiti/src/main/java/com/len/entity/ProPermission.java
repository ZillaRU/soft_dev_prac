package com.len.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


@ToString
@Data
@Getter
@Setter
@Table(name = "pro_permission")

public class ProPermission {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "pro_id")
    private String proId;

    @Column(name = "pro_name")
    private String proName;

    @Column(name = "pm_id")
    private String pmId;

    @Column(name = "pm_name")
    private String pmName;

    @Column(name = "pro_role_name")
    private String proRoleName;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "permi_in_email")
    private String permiInEmail;

    @Column(name = "permi_doc")
    private String permiDoc;

    @Column(name = "permi_git")
    private String permiGit;

    public void setId(String id) {
        this.id = id;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public void setPmId(String pmId) {
        this.pmId = pmId;
    }

    public void setPmName(String pmName) {
        this.pmName = pmName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void addPermission(String permission) {
        if (permission.equals("permiDoc")) {
            this.permiDoc = "yes";
        } else if (permission.equals("permiGit")) {
            this.permiGit = "yes";
        } else {
            this.permiInEmail = "yes";
        }
    }

    public void cancelPermission(String permission) {
        if (permission.equals("permiDoc")) {
            this.permiDoc = "no";
        } else if (permission.equals("permiGit")) {
            this.permiGit = "no";
        } else {
            this.permiInEmail = "no";
        }
    }

    public void setProRoleName(String proRoleName) {
        this.proRoleName = proRoleName;
    }

    public String getProRoleName() {
        return this.proRoleName;
    }
}
