package com.len.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.len.base.BaseController;
import com.len.entity.SysLog;
import com.len.exception.MyException;
import com.len.mapper.SysLogMapper;
import com.len.util.JsonUtil;
import com.len.util.ReType;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhuxiaomeng
 * @date 2017/12/29.
 * @email 154040976@qq.com
 *
 * 日志监控
 */
@Controller
@RequestMapping(value = "/log")
public class LogController  extends BaseController{
    private static final Logger logger= Logger.getLogger(LogController.class);
    @Autowired
    private SysLogMapper logMapper;

    @GetMapping(value = "showLog")
    public String showMenu(Model model){
        return "/system/log/logList";
    }

    /**
     * 日志监控
     * @param sysLog
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = "showLogList")
    @ResponseBody
    public String showLog(SysLog sysLog, String page, String limit){
        List<SysLog> tList=null;
        Page<SysLog> tPage= PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        try{
            tList=logMapper.selectListByPage(sysLog);
        }catch (MyException e){
            logger.error("class:LogController ->method:showLog->message:"+e.getMessage());
            e.printStackTrace();
        }
        ReType reType=new ReType(tPage.getTotal(),tList);
        return JSON.toJSONString(reType);
    }

    /**
     * 删除log
     * @param
     * @return
     */
    @PostMapping(value = "del")
    @ResponseBody
    public JsonUtil del(String[] ids){
        JsonUtil j=new JsonUtil();
        String msg="删除成功";
        try{
            for(String id:ids)
            logMapper.deleteByPrimaryKey(Integer.valueOf(id));
        }catch (MyException e){
            msg="删除失败";
            logger.error(msg+e.getMessage());
        }
        j.setMsg(msg);
        return j;
    }


}
