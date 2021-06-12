package com.upup.demo.postsystem.bss.schedule;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 * @Date 2021/2/14 23:07
 */
@SpringBootTest
class DailyCreateEsIndexTaskTest {

    @Autowired
    DailyCreateEsIndexTask dailyCreateEsIndexTask;

    @Test
    public void test0(){
        dailyCreateEsIndexTask.createLogIndex();
    }
}
