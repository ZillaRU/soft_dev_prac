package com.len.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "rsk_re_usr")
public class RskReUsr {

    @Id
    private Integer id;

    @Column(name = "H_ID")
    private String hId;

    @Column(name = "U_ID")
    private String uId;

    @Column(name = "U_Name")
    private String uName;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

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
     * @return U_ID
     */
    public String getuId() {
        return uId;
    }

    /**
     * @param uId
     */
    public void setuId(String uId) {
        this.uId = uId == null ? null : uId.trim();
    }

    /**
     * @return U_Name
     */
    public String getuName() {
        return uName;
    }

    /**
     * @param uName
     */
    public void setuName(String uName) {
        this.uName = uName == null ? null : uName.trim();
    }
}