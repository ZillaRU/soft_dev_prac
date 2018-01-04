package com.len.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SysLog implements Serializable {
    private Integer id;

    private String userName;

    private String ip;

    private String type;

    private String text;

    private String param;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}