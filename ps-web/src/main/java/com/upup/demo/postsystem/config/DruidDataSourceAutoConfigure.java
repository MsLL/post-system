package com.upup.demo.postsystem.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.upup.demo.postsystem.util.PostSystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 
 * @Date 2021/1/30 下午3:14 doc https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter imitate
 * com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure
 */
@Configuration
public class DruidDataSourceAutoConfigure {
    @Autowired
    PostSystemConfig postSystemConfig;

    @Autowired
    Environment environment;

    @Bean(value = "mysqlDataSource", initMethod = "init")
    //NOTE-UPUP 2021/3/18 上午12:04 : notice ： 按照文档或者copy DruidDataSourceAutoConfigure 类直接返回java.sql.DataSource是起不来的，因为java.sql.DataSource类确实没有名叫init的方法。所以这里返回的是一个DruidDataSource
    public DruidDataSource dataSourceOne() {
        final String prefix = "spring.datasource.druid.mysql0.";
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        druidDataSource.setUrl(environment.getProperty(prefix + "url"));
        druidDataSource.setUsername(postSystemConfig.getValue(environment.getProperty(prefix + "username")));
        druidDataSource.setPassword(postSystemConfig.getValue(environment.getProperty(prefix + "password")));
        druidDataSource.setInitialSize(10);
        druidDataSource.setMaxActive(20);
        druidDataSource.setKeepAlive(true);
        return druidDataSource;
    }
}
