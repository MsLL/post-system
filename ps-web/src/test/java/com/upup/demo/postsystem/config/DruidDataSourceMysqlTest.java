package com.upup.demo.postsystem.config;

import com.alibaba.druid.pool.DruidDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 * @Date 2021/1/30 下午3:51
 */
@SpringBootTest
class DruidDataSourceMysqlTest {

    @Autowired
    @Qualifier("mysqlDataSource")
    DataSource dataSource;

    @Test
    public void test0() throws SQLException {
        DruidDataSource druidDataSource=(DruidDataSource)dataSource;
        System.out.println(druidDataSource.getStatData());

        Connection connection=dataSource.getConnection();
        System.out.println(dataSource.getConnection().getClientInfo());
    }
}