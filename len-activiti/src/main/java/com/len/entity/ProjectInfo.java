package com.len.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Table(name = "project_info")
@Data
@ToString
public class ProjectInfo {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "pm_id")
    private String pmId;

    @Column(name = "proj_customer")
    private String projCustomer;

    @Column(name = "proj_name")
    @NotEmpty(message = "项目名不能为空")
    private String projName;

    @Column(name = "proj_no")
    private String projNo;

    @Column(name = "proj_state")
    private Integer projState;

    @Column(name = "proj_tech")
    private String projTech;

    private String milestone;

    @Column(name = "proj_domain")
    private String projDomain;

    @Column(name = "proj_main_func")
    private String proMainFunc;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "epg_leader")
    private String epgLeader;

    @Column(name = "config_manager")
    private String configManager;

    @Column(name = "qa_manager")
    private String qaManager;
}