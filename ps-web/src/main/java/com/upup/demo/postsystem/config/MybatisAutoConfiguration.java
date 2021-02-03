package com.upup.demo.postsystem.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author tao.li
 * @Date 2021/1/31 下午11:57
 */
@Configuration
//指定需要代理的DAO类位置 就是各个dao interface
@MapperScan({"com.upup.demo.postsystem.**.dao"})
public class MybatisAutoConfiguration {
}
