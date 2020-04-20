package com.len.core.annotation;

import java.lang.annotation.*;

/**
 * @author zhuxiaomeng
 * @date 2017/12/28.
 * @email 154040976@qq.com
 * <p>
 * 记录日志
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
@Inherited
public @interface Log {
    public enum LOG_TYPE {ADD, UPDATE, DEL, SELECT, ATHOR}

    ;

    /**
     * 内容
     */
    String desc();

    /**
     * 类型 curd
     */
    LOG_TYPE type() default LOG_TYPE.ATHOR;
}
