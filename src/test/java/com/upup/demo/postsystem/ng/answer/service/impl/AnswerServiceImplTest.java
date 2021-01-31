package com.upup.demo.postsystem.ng.answer.service.impl;

import com.google.gson.Gson;
import com.upup.demo.postsystem.ng.answer.entity.Answer;
import com.upup.demo.postsystem.ng.answer.service.AnswerService;
import javax.sound.midi.Soundbank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author tao.li
 * @Date 2021/1/31 下午11:47
 */
@SpringBootTest
class AnswerServiceImplTest {
    @Autowired
    AnswerService answerService;

    @Autowired Gson gson;

    @Test
    public void test0(){
        Answer answer=answerService.queryById(1);
        System.out.println(gson.toJson(answer));
    }
}