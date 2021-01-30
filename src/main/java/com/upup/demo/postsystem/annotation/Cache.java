package com.upup.demo.postsystem.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;

/**
 * @Author tao.li
 * @Date 2021/1/30 上午3:20
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {
    @AliasFor("expireTime")
    int value();

    /**
     *seconds
     */
    int expireTime();
}
