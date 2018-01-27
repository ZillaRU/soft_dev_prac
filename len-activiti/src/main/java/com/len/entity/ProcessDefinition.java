package com.len.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zhuxiaomeng
 * @date 2018/1/21.
 * @email 154040976@qq.com
 * 流程定义实体
 */
@Getter
@Setter
@ToString
public class ProcessDefinition {
  private String id;
  private String category;
  private String name;
  private String key;
  private String description;
  private int version;
  private String resourceName;
  private String deploymentId;
  private String diagramResourceName;
  private boolean hasStartFormKey;
  private boolean hasGraphicalNotation;
  private boolean isSuspended;
  private String tenantId;

  public ProcessDefinition() {
  }
  public ProcessDefinition(org.activiti.engine.repository.ProcessDefinition p) {
    this.id=p.getId();
    this.category=p.getCategory();
    this.name=p.getName();
    this.key=p.getKey();
    this.description=p.getDescription();
    this.version=p.getVersion();
    this.resourceName=p.getResourceName();
    this.deploymentId=p.getDeploymentId();
    this.diagramResourceName=p.getDiagramResourceName();
    this.hasStartFormKey=p.hasStartFormKey();
    this.hasGraphicalNotation=p.hasGraphicalNotation();
    this.isSuspended=p.isSuspended();
    this.tenantId=p.getTenantId();

  }
}
