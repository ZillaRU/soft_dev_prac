package com.len.util;


import com.len.base.CurrentUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import java.io.File;

/**
 * @author zhuxiaomeng
 * @date 2017/12/4.
 * @email 154040976@qq.com
 *
 * 管理工具类
 */
public class CommonUtil {
  /**
   * 获取当前用户
   */
  public static CurrentUser getUser() {
    org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
    Session session = subject.getSession();
    return (CurrentUser) session.getAttribute("currentPrincipal");
  }

  /**
   * 创建指定目录
   *
   * @param folderPath 目录地址
   * @return 目录地址
   */
  public static final String createFolderPath(String folderPath) {
    File file = new File(folderPath);
    if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
      String[] diskNames = {"A:", "B:", "C:", "D:", "E:", "F:", "G:", "H:", "I:", "J:", "K:", "L:", "M:", "N:",
              "O:", "P:", "Q:", "R:", "S:", "T:", "U:", "V:", "W:", "X:", "Y:", "Z:"};
      for (int i = 0; i < diskNames.length; i++) {
        if (i > 0) {
          folderPath = folderPath.replace(diskNames[i - 1], diskNames[i]);
        } else {
          folderPath = diskNames[0] + folderPath;
        }
        file = new File(folderPath);
        if (!file.exists()) {
          file.mkdirs();
        }
        if (file.exists()) {
          return folderPath;
        }
      }
      return null;
    } else {
      if (!file.exists()) {
        file.mkdirs();
      }
      if (file.exists()) {
        return folderPath;
      }
      return null;
    }
  }
}



  /**
   * 获取权限
   * @return
   */
  /*public static List<SysPermission> getPermission(){
    SysUser user=CommonUtil.getUser();
    if(user!=null){

    }
  }

}
*/