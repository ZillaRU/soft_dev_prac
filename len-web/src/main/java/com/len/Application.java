package com.len;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author zhuxiaomeng
 * @date 2018/1/1.
 * @email 154040976@qq.com
 */

@EnableWebMvc
@SpringBootApplication
@MapperScan(basePackages = "com.len.mapper")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class,args);
  }

}
