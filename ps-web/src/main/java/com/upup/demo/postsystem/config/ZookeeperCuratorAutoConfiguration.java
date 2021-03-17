package com.upup.demo.postsystem.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author tao.li
 * @Date 2021/3/6 下午9:58
 */
@Configuration
@EnableConfigurationProperties(ZookeperProperties.class)
public class ZookeeperCuratorAutoConfiguration {
    @Autowired
    private ZookeperProperties zookeperProperties;

    @Bean
    public CuratorFramework getClient() {
        CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString(zookeperProperties.getConnectString())
            .connectionTimeoutMs(zookeperProperties.getConnectionTimeoutMs())
            .sessionTimeoutMs(zookeperProperties.getSessionTimeoutMs())
            .retryPolicy(new RetryNTimes(3, 10 * 1000))
            .build();
        //NOTE-UPUP 2021/3/18 上午12:05 :   要client显式start，不然报：Expected state [STARTED] was [LATENT]
        client.start();
        return client;
    }
}
