package com.len.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SysRoleUser implements Serializable {
    private String userId;

    private String roleId;

    private static final long serialVersionUID = 1L;
}