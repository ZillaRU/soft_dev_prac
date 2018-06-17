package com.len.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "sys_menu")
@Data
@ToString
@EqualsAndHashCode
public class SysMenu {
    @Id
    @GeneratedValue(generator = "JDBC")
    private String id;

    private String name;

    @Column(name = "p_id")
    private String pId;

    private String url;

    /**
     * 排序字段
     */
    @Column(name = "order_num")
    private Integer orderNum;

    /**
     * 图标
     */
    private String icon;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 权限
     */
    private String permission;

    /**
     * 1栏目2菜单
     */
    @Column(name = "menu_type")
    private Byte menuType;

    private int num;

    private List<SysRole> roleList;

    private static final long serialVersionUID = 1L;

    private List<SysMenu> children=new ArrayList<SysMenu>();

    public void addChild(SysMenu sysMenu){
        children.add(sysMenu);
    }
}