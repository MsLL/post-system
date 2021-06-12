package com.upup.demo.postsystem.config;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * 
 * @Date 2021/1/30 下午10:00
 * 一个空Component,只是为了声明@EnableScheduling注解。
 * 没有直接标注在PostSystemApplication类上。那样不优雅。配置统一放在config包下。
 */

@EnableScheduling
@Component
public class ScheduleConfigure {
}
