package com.len;

import java.util.Arrays;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
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
    ApplicationContext applicationContext=SpringApplication.run(Application.class,args);
    String[] names = applicationContext.getBeanDefinitionNames();
    Arrays.asList(names).forEach(name -> System.out.println(name));//打印bean
   /* SchedulerFactory schedulerFactory = new StdSchedulerFactory();
    Scheduler scheduler = schedulerFactory.getScheduler();
    JobDetail jobDetail = JobBuilder.newJob(ScheduledTasks.class).withIdentity("testkey", "testvalue").withDescription("一个测试的类").build();
    jobDetail.getJobDataMap().put("ConfigurableApplicationContext",applicationContext);//重点是这句话
    Trigger trigger = TriggerBuilder.newTrigger().startNow().withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?")).startNow().build();
    scheduler.scheduleJob(jobDetail,trigger);
    scheduler.start();*/
  }

}
