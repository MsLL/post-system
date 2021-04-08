package com.upup.demo.postsystem.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author tao.li
 * @Date 2021/4/8 下午10:29
 */
@Getter
@Setter
@ConfigurationProperties("spring.datasource.druid.mysql")
public class DruidDataSourceMysqlProperties {
    String url;
    String username;
    String password;
}
