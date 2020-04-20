package com.len.core.shiro;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
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
public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher {

    private Cache<String, AtomicInteger> loginRetryCache;

    private int maxRetryCount = 5;

    public void setMaxRetryCount(int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }
    public RetryLimitCredentialsMatcher(){
    }

    /**
     *
     * @param cacheManager
     * @param maxRetryCount 最大尝试次数
     */
    public RetryLimitCredentialsMatcher(CacheManager cacheManager,int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
        this.loginRetryCache = cacheManager.getCache("loginRetryCache");
    }

    public RetryLimitCredentialsMatcher(CacheManager cacheManager){
        this(cacheManager,5);
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        //retry count + 1
        AtomicInteger retryCount = loginRetryCache.get(username) == null
                ? new AtomicInteger(0) : loginRetryCache.get(username);
        log.info("retryCount:{}, username:{}",retryCount,username);
        if (retryCount.incrementAndGet() > this.maxRetryCount) {
            log.warn("username: {} tried to login more than {} times in perid", username,this.maxRetryCount);
            throw new ExcessiveAttemptsException(StrUtil.format("username: {} tried to login more than {} times in perid", username,this.maxRetryCount));
        }
        boolean matches = super.doCredentialsMatch(token, info);

        if (matches) {
            loginRetryCache.remove(username);
        } else {
            loginRetryCache.put(username, retryCount);
            log.info(String.valueOf(retryCount.get()));
        }
        return matches;
    }
}  