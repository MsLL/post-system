package com.upup.demo.postsystem;

import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author tao.li
 * @Date 2021/1/25 下午8:47
 */

@SpringBootTest
class PostSystemApplicationTest {

    @Autowired
    Environment environment;

    @Test
    public void test0(){
        System.out.println(Arrays.toString(environment.getDefaultProfiles()));
        System.out.println(Arrays.toString(environment.getActiveProfiles()));

        System.out.println(environment.getProperty("upup.xxx"));
    }

    public void test1(){

    }
}