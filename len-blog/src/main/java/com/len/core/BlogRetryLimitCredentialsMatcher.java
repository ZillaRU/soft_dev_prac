package com.len.core;

import cn.hutool.core.util.StrUtil;
import com.len.util.CustomUsernamePasswordToken;
import com.len.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 验证器，增加了登录次数校验功能
 * 限制尝试登陆次数,防止暴力破解
 */
@Slf4j
public class BlogRetryLimitCredentialsMatcher extends HashedCredentialsMatcher {

   /* private Cache<String, AtomicInteger> loginRetryCache;

    private int maxRetryCount = 5;

    *//*public void setMaxRetryCount(int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }
    public BlogRetryLimitCredentialsMatcher(){
    }*//*

    *//**
     * @param cacheManager
     * @param maxRetryCount 最大尝试次数
     *//*
    public BlogRetryLimitCredentialsMatcher(CacheManager cacheManager, int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
        this.loginRetryCache = cacheManager.getCache("loginRetryCache");
    }

    public BlogRetryLimitCredentialsMatcher(CacheManager cacheManager) {
        this(cacheManager, 5);
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        CustomUsernamePasswordToken token1 = (CustomUsernamePasswordToken) token;
        String username = token1.getUsername();
        if (StringUtils.isEmpty(username) && !StringUtils.isEmpty(token1.getToken())) {
            username = JWTUtil.getUsername(token1.getToken());
        }
        //retry count + 1
        AtomicInteger retryCount = loginRetryCache.get(username) == null
                ? new AtomicInteger(0) : loginRetryCache.get(username);
        log.info("retryCount:{}, username:{}", retryCount, username);
        if (retryCount.incrementAndGet() > this.maxRetryCount) {
            log.warn("username: {} tried to login more than {} times in perid", username, this.maxRetryCount);
            throw new ExcessiveAttemptsException(StrUtil.format("username: {} tried to login more than {} times in perid", username, this.maxRetryCount));
        }
        boolean matches = super.doCredentialsMatch(token1, info);

        if (matches) {
            loginRetryCache.remove(username);
        } else {
            loginRetryCache.put(username, retryCount);
            log.info(String.valueOf(retryCount.get()));
        }
        return matches;
    }*/
}  