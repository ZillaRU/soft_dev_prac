package com.len.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "pro_wor_info_man")
public class ProWorInfoMan {
    @Id
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "pro_id")
    private String proId;

    @Column(name = "pro_name")
    private String proName;

    @Column(name = "pm_id")
    private String pmId;

    @Column(name = "pro_role_name")
    private String proRoleName;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_phone")
    private String userPhone;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * @return pro_id
     */
    public String getProId() {
        return proId;
    }

    /**
     * @param proId
     */
    public void setProId(String proId) {
        this.proId = proId == null ? null : proId.trim();
    }

    /**
     * @return pro_name
     */
    public String getProName() {
        return proName;
    }

    /**
     * @param proName
     */
    public void setProName(String proName) {
        this.proName = proName == null ? null : proName.trim();
    }

    /**
     * @return pm_id
     */
    public String getPmId() {
        return pmId;
    }

    /**
     * @param pmId
     */
    public void setPmId(String pmId) {
        this.pmId = pmId == null ? null : pmId.trim();
    }

    /**
     * @return pro_role_name
     */
    public String getProRoleName() {
        return proRoleName;
    }

    /**
     * @param proRoleName
     */
    public void setProRoleName(String proRoleName) {
        this.proRoleName = proRoleName == null ? null : proRoleName.trim();
    }

    /**
     * @return user_email
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * @param userEmail
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    /**
     * @return user_phone
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * @param userPhone
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }
}