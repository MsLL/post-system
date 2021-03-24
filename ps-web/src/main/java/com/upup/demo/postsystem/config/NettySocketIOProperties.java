package com.upup.demo.postsystem.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "netty-socket-io")
@Getter
@Setter
public class NettySocketIOProperties {
    /**
     * socket端口
     */
    private Integer serverSocketPort = 9076;
    /**
     * Ping消息间隔（毫秒）
     */
    private Integer pingInterval = 8 * 60 * 60 * 1000;//8 hour
    /**
     * Ping消息超时时间（毫秒）
     */
    private Integer pingTimeout = 8 * 60 * 60 * 1000;//8 hour;
    /**
     * 打开的HTMl文件
     */
    private String indexHtml;

}
