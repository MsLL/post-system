package com.upup.demo.postsystem.bss.schedule;

import java.time.LocalDateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author tao.li
 * @Date 2021/1/30 下午9:56
 * see https://juejin.cn/post/6844903968292732941
 * @EnableScheduling注解 声明在ScheduleConfigure类上，没有直接标注在PostSystemApplication类上。那样不优雅。配置统一放在config目录下。
 */


@Component
public class DemoScheduledTask {

    //@Scheduled(fixedRate = 3000)
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void scheduledTask() {
        System.out.println("      Demo Scheduled 任务执行时间：" + LocalDateTime.now());
    }

}
