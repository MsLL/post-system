package com.upup.demo.postsystem.util;

import java.util.Arrays;

/**
 * 
 * @Date 2021/2/14 23:04
 */
public class SoutExceptionUtil {
    /**
     * 打印异常信息到标准输出
     */
    public static void soutException(Throwable e) {
        System.out.println(e);

        String[] stack = Arrays.stream(e.getStackTrace()).map(stackTraceElement -> stackTraceElement.toString()).toArray(String[]::new);
        System.out.println(String.join(System.getProperty("line.separator"), Arrays.asList(stack)));
    }
}
