package com.len.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.activiti.engine.repository.Deployment;

/**
 * @author zhuxiaomeng
 * @date 2018/1/15.
 * @email 154040976@qq.com
 * 流程部署表
 */
@Getter
@Setter
public class ActDeployment implements Serializable {
  private String id;
  private String name;
  private Date deploymentTime;
  private String category;
  private String tenantId;

  public ActDeployment() {
  }

  public ActDeployment(Deployment deployment) {
    this.id = deployment.getId();
    this.name = deployment.getName();
    this.deploymentTime = deployment.getDeploymentTime();
    this.category = deployment.getCategory();
    this.tenantId = deployment.getTenantId();
  }
}
