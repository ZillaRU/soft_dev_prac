package com.len.controller;

import com.len.entity.SysUser;
import com.len.service.SysUserService;
import com.len.util.JWTUtil;
import com.len.util.Md5Util;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuxiaomeng
 * @date 2018/7/25.
 * @email 154040976@qq.com
 */
@RestController
@RequestMapping("/blog")
public class SignController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
        SysUser user=new SysUser();
        user.setUsername(username);
        SysUser sysUser=sysUserService.selectOne(user);
        String pass = Md5Util.getMD5(password, username);
        if (sysUser.getPassword().equals(pass)) {
            return JWTUtil.sign(username, password);
        } else {
            throw new UnauthorizedException();
        }
    }
}
