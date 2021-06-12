package com.upup.demo.postsystem.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 * @Date 2021/1/29 下午10:59
 */

@SpringBootTest
class RedisTemplateTest {
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void test0() {
        assertNotNull(redisTemplate);
        assertNotNull(stringRedisTemplate);

        assertTrue(stringRedisTemplate.hasKey("runoobkey"));

        System.out.println(stringRedisTemplate.opsForValue().get("str1"));
        System.out.println(stringRedisTemplate.opsForValue().get("str2"));
        System.out.println(stringRedisTemplate.opsForList().range("list1", 0, -1));
        System.out.println(stringRedisTemplate.opsForHash().entries("map1"));
        System.out.println(stringRedisTemplate.opsForSet().members("set1"));
        //assertTrue(redisTemplate.hasKey("runoobkey"));
    }
}