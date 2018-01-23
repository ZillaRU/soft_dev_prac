package com.len.base;

import com.len.util.JsonUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @author zhuxiaomeng
 * @date 2017/12/19.
 * @email 154040976@qq.com
 */
public abstract class BaseController<T> {

  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class, new CustomDateEditor(
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
    binder.registerCustomEditor(Date.class, new CustomDateEditor(
        new SimpleDateFormat("yyyy-MM-dd"), true));
  }

  @ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
  public String authorizationException(HttpServletRequest request, HttpServletResponse response) {
    if (isAjaxRequest(request)) {
      Map<String,Object> map = new HashMap<>();
      map.put("code", "-998");
      map.put("message", "无权限");
     //response.gets
      return null;
    } else {
      String message="权限不足";
      try {
        message = URLEncoder.encode(message,"utf-8");
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
      return "redirect:/error/403?message="+message;
    }
  }
  public static boolean isAjaxRequest(HttpServletRequest request) {
    String requestedWith = request.getHeader("x-requested-with");
    if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
      return true;
    } else {
      return false;
    }
  }


}
