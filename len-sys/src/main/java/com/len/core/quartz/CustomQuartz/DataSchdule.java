package com.len.core.quartz.CustomQuartz;

import java.io.IOException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
 * @author zhuxiaomeng
 * @date 2018/1/29.
 * @email 154040976@qq.com
 *
 * 定时还原数据库数据
 */

@Component
public class DataSchdule {

    //@Scheduled(cron="0/5 * *  * * ? ")
    public static void restData() throws IOException, InterruptedException {
        String sqlPath =  "G:\\os\\sql\\lenos_test.sql";  // SQL文件路径
        String[] execCMD = new String[]{"mysql", "lenos_test", "-u" + "root", "-p" , "-e source", sqlPath};
        Process process = Runtime.getRuntime().exec(execCMD);

        int processComplete = process.waitFor();
        if (processComplete == 0) {
            System.out.println("还原成功.");
        } else {
            throw new RuntimeException("还原数据库失败.");
        }
    }
}