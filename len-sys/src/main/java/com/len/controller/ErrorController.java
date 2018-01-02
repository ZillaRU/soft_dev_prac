package com.len.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhuxiaomeng
 * @date 2017/12/19.
 * @email 154040976@qq.com
 * 404 403 500
 */
@Controller
@RequestMapping(value = "/error")
public class ErrorController {

  @GetMapping(value = "404")
  public String pageNotFound(){
  return "error/404";
}

}
