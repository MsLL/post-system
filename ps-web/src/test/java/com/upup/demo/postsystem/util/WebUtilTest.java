package com.upup.demo.postsystem.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author tao.li
 * @Date 2021/1/29 下午10:19
 */
class WebUtilTest {

    @Test
    public void testgeneratePserId(){
        System.out.println(WebUtil.generatePserId());
    }

    @Test
    public void testgetPserId(){
        System.out.println(WebUtil.getPserId("e02c4bf2ecf3a0bc77bfa6db589e18c6a7055bcf1228382b792864404d3a1eff0fa105a91cea0b3f0c7998f2a5228aa2"));
    }

}