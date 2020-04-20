package com.len.util;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author zhuxiaomeng
 * @date 2018/8/18.
 * @email 154040976@qq.com
 */
public class CustomUsernamePasswordToken extends UsernamePasswordToken {

    private String type;



    public CustomUsernamePasswordToken(final String username, final String password, String loginType) {
        super(username,password);
        this.type = loginType;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
