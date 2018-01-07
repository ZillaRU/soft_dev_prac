package com.len.core.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class MySchedulerListener {
      
    @Autowired  
    MyJobFactory myJobFactory;  

      
    @Bean(name ="mySchedulerFactoryBean")
    public SchedulerFactoryBean mySchedulerFactory() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();  
        bean.setOverwriteExistingJobs(true);  
        bean.setStartupDelay(1);  
        bean.setJobFactory(myJobFactory);  
        return bean;  
    }  
  
}  