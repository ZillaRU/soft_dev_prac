package com.len.util;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhuxiaomeng
 * @date 2017/12/19.
 * @email 154040976@qq.com
 * 查询返回json格式依照ui默认属性名称
 */
public class ReType implements Serializable{
  /**状态*/
  public int code=0;
  /**状态信息*/
  public String msg="";
  /**数据总数*/
  public long count;

  public List<?> data;

  public ReType() {
  }

  public ReType(long count, List<?> data) {
    this.count = count;
    this.data = data;
  }
}
