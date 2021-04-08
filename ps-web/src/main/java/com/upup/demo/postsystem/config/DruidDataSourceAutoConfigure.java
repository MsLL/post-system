package com.upup.demo.postsystem.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.upup.demo.postsystem.util.PostSystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author tao.li
 * @Date 2021/1/30 下午3:14 doc https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter imitate
 * com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure
 */
@EnableConfigurationProperties(DruidDataSourceMysqlProperties.class)
@Configuration
public class DruidDataSourceAutoConfigure {

    @Autowired
    DruidDataSourceMysqlProperties druidDataSourceMysqlProperties;

    @Autowired
    PostSystemConfig postSystemConfig;

    @Bean(value = "mysqlDataSource", initMethod = "init")
    //NOTE-UPUP 2021/3/18 上午12:04 : notice ： 按照文档或者copy DruidDataSourceAutoConfigure 类直接返回java.sql.DataSource是起不来的，因为java.sql.DataSource类确实没有名叫init的方法。所以这里返回的是一个DruidDataSource
    public DruidDataSource dataSourceOne() {
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        druidDataSource.setUrl(druidDataSourceMysqlProperties.url);
        druidDataSource.setUsername(postSystemConfig.getValue(druidDataSourceMysqlProperties.getUsername()));
        druidDataSource.setPassword(postSystemConfig.getValue(druidDataSourceMysqlProperties.getPassword()));
        return  druidDataSource;
    }
}
