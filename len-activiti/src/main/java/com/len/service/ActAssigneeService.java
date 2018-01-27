package com.len.service;

import com.len.base.BaseService;
import com.len.entity.ActAssignee;

/**
 * @author zhuxiaomeng
 * @date 2018/1/23.
 * @email 154040976@qq.com
 */
public interface ActAssigneeService extends BaseService<ActAssignee,String> {
  int deleteByNodeId(String nodeId);

}
