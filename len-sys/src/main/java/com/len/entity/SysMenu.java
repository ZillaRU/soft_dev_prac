package com.len.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.len.validator.group.AddGroup;
import com.len.validator.group.UpdateGroup;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SysMenu implements Serializable {
    private String id;

    @NotEmpty(message = "菜单名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String name;

    private String pId;

    private String url;

    @Length(min = 1,max = 4, message = "序号长度不对")
    private Integer orderNum;

    private String icon;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private String permission;

    private Byte menuType;
    /**菜单排序id 填充菜单展示id*/
    private int num;

    private List<SysRole> roleList;

    private static final long serialVersionUID = 1L;

    private List<SysMenu> children=new ArrayList<SysMenu>();

    public void addChild(SysMenu sysMenu){
        children.add(sysMenu);
    }
}