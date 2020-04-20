package com.len.entity;

import com.len.validator.group.AddGroup;
import com.len.validator.group.UpdateGroup;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Table(name = "sys_user")
@Data
@ToString
public class SysUser {
    @Id
    private String id;

    @NotEmpty(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String username;

    @NotEmpty(message = "密码不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String password;

    private Integer age;

    private String email;

    private String phone;

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

    private String department;

    @Column(name = "chief_id")
    private String chiefId;

    /**
     * 0可用1封禁
     */
    @Column(name = "del_flag")
    private Byte delFlag;

    public String getUsername() {
        return this.username;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPhone(){
        return this.phone;
    }
}
