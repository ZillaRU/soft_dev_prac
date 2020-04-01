package com.len.entity;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "pro_worker_info")
@ToString
@Data
public class ProjectWorkerInfo extends BaseTask {
    @Column(name = "pm_id")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "JDBC")
    protected String pm_id;


}
