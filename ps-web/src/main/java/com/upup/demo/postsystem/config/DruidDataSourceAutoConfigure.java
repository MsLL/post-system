package com.upup.demo.postsystem.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author tao.li
 * @Date 2021/1/30 下午3:14
 * doc https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter
 * imitate com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure
 */
@Configuration
public class DruidDataSourceAutoConfigure {

    @Bean(value = "mysqlDataSource",initMethod = "init")
    @ConfigurationProperties("spring.datasource.druid.mysql")
    //notice ： 按照文档或者copyDruidDataSourceAutoConfigure 类直接返回java.sql.DataSource是起不来的，因为这个类确实没有名叫init的方法。所以这里返回的是一个DruidDataSource
    public DruidDataSource dataSourceOne() {
        return DruidDataSourceBuilder.create().build();
    }
}
