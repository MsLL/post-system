package com.upup.demo.postsystem.config;

import org.apache.curator.framework.CuratorFramework;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 
 * @Date 2021/3/6 下午10:18
 */
@SpringBootTest
class ZookeeperCuratorAutoConfigurationTest {
    @Autowired
    CuratorFramework client;

    /**
     * 同ls /
     */
    @Test
    public void testListRoot() throws Exception {
        client.getChildren().forPath("/").forEach(str -> System.out.println(str));
    }
}