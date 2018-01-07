package com.len.core.BootListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author zhuxiaomeng
 * @date 2018/1/6.
 * @email 154040976@qq.com
 *
 * 通过监听，开辟线程，执行定时任务 当然 也可以执行其他
 */
@Component
public class MyApplicationListener  implements ApplicationListener<ContextRefreshedEvent> {

  Logger logger= LoggerFactory.getLogger(MyApplicationListener.class);



  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    logger.info("-------------bean初始化完毕-------------");
    /**
     * 通过线程开启数据库中已经开启的定时任务 灵感来自spring
     * spring boot继续执行 mythread开辟线程，延迟后执行
     */
    DataSourceJobThread myThread= (DataSourceJobThread) event.getApplicationContext().getBean(
        "dataSourceJobThread");
    myThread.start();
  }

}
