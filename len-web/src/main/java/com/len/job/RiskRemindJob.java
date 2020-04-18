package com.len.job;

import com.len.core.websocket.ProductWebSocket;
import com.len.entity.*;
import com.len.service.*;
import com.len.service.impl.MailService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Slf4j
public class RiskRemindJob implements Job {
//每周提示项目经理召集相关人员识别和跟踪风险

    @Autowired
    RoleUserService sysRoleUserService;

    @Autowired
    RoleService sysRoleService;

    @Autowired
    MailService mailService;

    @Autowired
    RiskInfoService riskInfoService;

    @Autowired
    RskReUsrService rskReUsrService;

    @Autowired
    SysUserService sysUserService;

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("JobRisk：启动任务=======================");
        run();
        System.out.println("JobRisk：下次执行时间=====" +
                new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                        .format(context.getNextFireTime()) + "==============");
    }

    public void run() throws IOException {

        ProductWebSocket.sendInfo("项目经理需要每周召集相关人员识别和跟踪风险的噢(～￣▽￣)～");
        List<SysUser> sysUserList = sysUserService.selectAll();
        for (SysUser sysUser : sysUserList) {
            SysRoleUser sysRoleUser = sysRoleUserService.selectByPrimaryKey(sysUser.getId());
            SysRole sysRole = sysRoleService.selectByPrimaryKey(sysRoleUser.getRoleId());
            if (sysRole.getRoleName().equals("pm")) {
                mailService.sendMail("风险管理提醒",
                        "项目经理需要每周召集相关人员识别和跟踪风险的噢(～￣▽￣)～",
                        sysUser.getEmail());
            }
        }

        List<RskInfo> rskInfoList = riskInfoService.selectAll();
        for (RskInfo rskInfo : rskInfoList) {
            if (rskInfo.gethFrequency() < 2) {
                List<RskReUsr> rskReUsrList = rskReUsrService.selectByRId(rskInfo.gethId());
                for (RskReUsr rskReUsr : rskReUsrList) {
                    SysUser sysUser = sysUserService.selectByPrimaryKey(rskReUsr.getuId());
                    System.out.println("风险跟踪提醒" +
                            "您编号为" + rskInfo.gethId() + "的一条风险信息，风险跟踪频度小于等于1次，请您尽快跟踪风险(～￣▽￣)～" +
                            sysUser.getEmail());

                    mailService.sendMail("风险跟踪提醒",
                            "您编号为" + rskInfo.gethId() + "的一条风险信息，风险跟踪频度小于等于1次，请您尽快跟踪风险(～￣▽￣)～",
                            sysUser.getEmail());
                }
            }
        }
        log.info("JobRisk：执行完毕=======================");
    }
}