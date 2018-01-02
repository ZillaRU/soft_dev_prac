package com.len.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuxiaomeng
 * @date 2018/1/1.
 * @email 154040976@qq.com
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

  @GetMapping(value = "/demo1")
  public String demo1(){
    return "hello spring boot";
  }

}
