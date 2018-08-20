package com.len.core.exception;

import com.len.util.JsonUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zhuxiaomeng
 * @date 2018/8/19.
 * @email 154040976@qq.com
 */
@RestControllerAdvice
public class CustomException {

    /**
     * 401
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({AuthenticationException.class, UnknownAccountException.class,UnauthorizedException.class})
    public JsonUtil getAuthenticationException(AuthenticationException e) {
        return new JsonUtil(false, e.getMessage(), 401);
    }
}
