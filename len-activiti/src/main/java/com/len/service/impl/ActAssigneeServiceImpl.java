package com.len.service.impl;

import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.ActAssignee;
import com.len.mapper.ActAssigneeMapper;
import com.len.service.ActAssigneeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhuxiaomeng
 * @date 2018/1/23.
 * @email 154040976@qq.com
 */
@Service
public class ActAssigneeServiceImpl extends BaseServiceImpl<ActAssignee,String> implements
    ActAssigneeService{

  @Autowired
  ActAssigneeMapper actAssigneeMapper;

  @Override
  public BaseMapper<ActAssignee, String> getMappser() {
    return actAssigneeMapper;
  }

  @Override
  public int deleteByNodeId(String nodeId) {
    return actAssigneeMapper.deleteByNodeId(nodeId);
  }
}
