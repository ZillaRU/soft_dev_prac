package com.len.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_user")
@Data
@ToString
public class SysUser {
    @Id
    @GeneratedValue(generator = "JDBC")
    private String id;

    private String username;

    private String password;

    private Integer age;

    private String email;

    private String photo;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 0可用1封禁
     */
    @Column(name = "del_flag")
    private Byte delFlag;
}