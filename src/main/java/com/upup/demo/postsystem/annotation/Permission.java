package com.upup.demo.postsystem.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;

/**
 * @Author tao.li
 * @Date 2021/1/27 下午11:52 imitate org.springframework.web.bind.annotation.RequestMapping
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {

    @AliasFor("permission")
    int[] value() default {};

    //String[] arr={};//{}, another way to create a array.
    //in annotition： method name as field name,method return type as field type
    int[] permission() default {};
}
