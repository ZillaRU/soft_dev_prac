package com.len.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SysRoleMenu implements Serializable {
    private String roleId;

    private String menuId;

    private static final long serialVersionUID = 1L;
}