package com.upup.demo.postsystem.bss.email;

import com.upup.demo.postsystem.bss.email.QQEMailSendUtil;
import org.junit.jupiter.api.Test;

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
