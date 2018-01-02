package com.len.core.shiro;

import java.util.concurrent.atomic.AtomicInteger;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * 验证器，增加了登录次数校验功能 
 */  
public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher {  
    private static final Logger log = LoggerFactory.getLogger(RetryLimitCredentialsMatcher.class);

    private Cache<String, AtomicInteger> loginRetryCache;
  
    private int maxRetryCount = 5;  
  
    private String lgoinRetryCacheName;  
  
    public void setMaxRetryCount(int maxRetryCount) {  
        this.maxRetryCount = maxRetryCount;  
    }  
  
    public RetryLimitCredentialsMatcher(CacheManager cacheManager,String lgoinRetryCacheName) {  
        this.lgoinRetryCacheName = lgoinRetryCacheName;
        loginRetryCache = cacheManager.getCache(lgoinRetryCacheName);
    }  
  
    @Override  
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {  
        String username = (String) token.getPrincipal();  
        //retry count + 1  
        AtomicInteger retryCount = loginRetryCache.get(username);
        if (null == retryCount) {  
            retryCount = new AtomicInteger(0);
            loginRetryCache.put(username, retryCount);
        }
        if (retryCount.incrementAndGet() > 5) {  
            log.warn("username: " + username + " tried to login more than 5 times in period");  
            throw new ExcessiveAttemptsException("username: " + username + " tried to login more than 5 times in period"  
            );  
        }
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            loginRetryCache.remove(username);
        }

        return matches;  
    }  
}  