package com.upup.demo.postsystem;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author tao.li
 * @Date 2021/1/27 上午4:17
 */
@Slf4j
public class ExceptionPrint {
    public static void main(String[] args) {
        log.error("", new NullPointerException().getCause());
        System.out.println();
        log.error("", new NullPointerException());
    }
}
