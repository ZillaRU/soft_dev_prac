package com.len.core.shiro;

import com.len.base.CurrentUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @author zhuxiaomeng
 * @date 2017/12/28.
 * @email 154040976@qq.com
 */
public class Principal {

    /**
     * 获取用户主题
     *
     * @return
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取当前用户对象
     * @return
     */
    public static CurrentUser getPrincipal() {
        return (CurrentUser) getSubject().getPrincipal();
    }

    /**
     * 当前session
     * @return
     */
    public static Session getSession() {
        return getSubject().getSession();
    }

    public static CurrentUser getCurrentUse() {
        return (CurrentUser) getSession().getAttribute("currentPrincipal");
    }

}
