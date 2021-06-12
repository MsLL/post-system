package com.upup.demo.postsystem.util;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 * @Date 2021/4/8 下午11:24
 */
@SpringBootTest
class PostSystemConfigTest {
    @Autowired
    PostSystemConfig postSystemConfig;

    @Test
    public void test0() {
        System.out.println(postSystemConfig.getValue("/objects/postsystem/mysql/dev/username"));
        System.out.println(postSystemConfig.getValue("/objects/postsystem/mysql/dev/password"));
    }

    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("127.0.0.1:2181")
            .connectionTimeoutMs(10 * 1000)
            .sessionTimeoutMs(60 * 1000)
            .retryPolicy(new RetryNTimes(3, 10 * 1000))
            .build();
        client.start();
        if (client.checkExists().forPath("/objects/postsystem/mysql/dev/username") == null) {
            client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                .forPath("/objects/postsystem/mysql/dev/username", "root".getBytes());
        }
        if (client.checkExists().forPath("/objects/postsystem/mysql/dev/password") == null) {
            client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                .forPath("/objects/postsystem/mysql/dev/password", "123456".getBytes());
        }
    }
}