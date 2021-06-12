package com.upup.demo.postsystem.enums;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 
 * @Date 2021/2/14 18:30
 */
class EnvTest {
    @Test
    public void test() {
        Arrays.stream(Env.values()).forEach(env -> {
            System.out.println(env.name());
        });
    }
}
