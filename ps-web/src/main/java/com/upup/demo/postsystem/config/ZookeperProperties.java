package com.upup.demo.postsystem.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * @Date 2021/3/6 下午9:56
 */
@ConfigurationProperties("zookeeper")
@Getter
@Setter
public class ZookeperProperties {
    private String connectString = "127.0.0.1:2181";
    private int connectionTimeoutMs = 10 * 1000;//连接超时时间，默认10秒
    private int sessionTimeoutMs = 60 * 1000;//会话超时时间，默认60秒
}
