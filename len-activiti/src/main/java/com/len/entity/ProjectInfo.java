package com.len.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Table(name = "project_info")
@ToString
@Data
public class ProjectInfo extends BaseTask {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "JDBC")
    protected String id;

    @Override
    public String getId() {
        return id;
    }

    @Column(name = "user_id")
    private String userId;

    @Override
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    @Column(name = "pm_id")
    private String pmId;

    @Column(name = "pm_name")
    private String pmName;

    @Column(name = "proj_customer")
    private String projCustomer;

    @Column(name = "proj_name")
    @NotEmpty(message = "项目名不能为空")
    private String projName;

    @Column(name = "proj_no")
    private String projNo;

    @Column(name = "proj_state")
    private String projState;

    @Column(name = "proj_tech")
    private String projTech;

    @Column(name = "milestone")
    private String milestone;

    @Column(name = "proj_domain")
    private String projDomain;

    @Column(name = "proj_main_func")
    private String projMainFunc;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "start_date")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "epg_manager")
    private String epgManager;

    @Column(name = "epg_name")
    private String epgName;

    @Column(name = "config_manager")
    private String configManager;

    @Column(name = "conf_name")
    private String confName;

    @Column(name = "qa_manager")
    private String qaManager;

    @Column(name = "qa_name")
    private String qaName;
}