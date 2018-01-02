package com.len.util;


import com.alibaba.fastjson.JSONObject;

/**
 * @author zhuxiaomeng
 * @date 2017/12/15.
 * @email 154040976@qq.com
 * ajax 回执
 */
public class JsonUtil {

  //默认成功
  private boolean flag=true;
  private String msg;
  private JSONObject josnObj;

  public boolean isFlag() {
    return flag;
  }

  public void setFlag(boolean flag) {
    this.flag = flag;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public JSONObject getJosnObj() {
    return josnObj;
  }

  public void setJosnObj(JSONObject josnObj) {
    this.josnObj = josnObj;
  }
}
