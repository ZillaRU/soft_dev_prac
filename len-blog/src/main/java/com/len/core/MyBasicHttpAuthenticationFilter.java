package com.len.core;

import com.len.core.exception.UnauthorizedException;
import com.len.util.JwtToken;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.security.auth.Subject;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhuxiaomeng
 * @date 2018/8/19.
 * @email 154040976@qq.com
 */
public class MyBasicHttpAuthenticationFilter extends BasicHttpAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            try {
                executeLogin(request, response);
                return true;
            } catch (Exception e) {
            }
        }
        return false;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Authorization");
        JwtToken jwtToken = new JwtToken(token, "BlogLogin");
        getSubject(request, response).login(jwtToken);
        return true;
    }

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("Authorization");
        return authorization != null;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED,"访问被拒绝");
        return false;
    }
}
