package com.upup.demo.postsystem.bss.qrcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author tao.li
 * @Date 2021/2/13 19:10
 */
class QrcodeUtilTest {
    @Test
    public void test0(){
        QrcodeUtil.parse(new byte[1]);
    }
}
