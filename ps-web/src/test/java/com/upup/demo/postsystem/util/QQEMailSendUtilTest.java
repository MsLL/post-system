package com.upup.demo.postsystem.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author tao.li
 * @Date 2021/1/30 下午8:13
 */
class QQEMailSendUtilTest {

    @Test
    public void test0(){
        QQEMailSendUtil.sendQQEamil(
            "This is the Title Line from Post System UT",
            "This is actual content body",
            "2213311288@qq.com"
        );
    }
}