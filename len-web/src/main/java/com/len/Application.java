package com.len;

import java.util.Arrays;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author zhuxiaomeng
 * @date 2018/1/1.
 * @email 154040976@qq.com
 */

@EnableWebMvc
@SpringBootApplication
@ComponentScan({"com.len","org.activiti"})
@MapperScan(basePackages = {"com.len.mapper"})
public class Application {

  public static void main(String[] args) {
    ApplicationContext applicationContext=SpringApplication.run(Application.class,args);
    String[] names = applicationContext.getBeanDefinitionNames();
    Arrays.asList(names).forEach(name -> System.out.println(name));//1.8 forEach循环
  }



}
