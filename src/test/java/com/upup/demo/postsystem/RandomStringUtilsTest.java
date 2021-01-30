package com.upup.demo.postsystem;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @Author tao.li
 * @Date 2021/1/30 下午4:43
 */
public class RandomStringUtilsTest {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String str = RandomStringUtils.random(6, true, true);
            System.out.println(str);
        }
    }
}
