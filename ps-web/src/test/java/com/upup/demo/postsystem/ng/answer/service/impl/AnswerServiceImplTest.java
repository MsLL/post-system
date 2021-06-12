package com.upup.demo.postsystem.ng.answer.service.impl;

import com.google.gson.Gson;
import com.upup.demo.postsystem.ng.answer.model.AnswerWrapper;
import com.upup.demo.postsystem.ng.answer.po.Answer;
import com.upup.demo.postsystem.ng.answer.service.AnswerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 
 * @Date 2021/1/31 下午11:47
 */
@SpringBootTest
class AnswerServiceImplTest {
    @Autowired
    AnswerService answerService;

    @Autowired Gson gson;

    @Test
    public void test0(){
        AnswerWrapper answer=answerService.findById(1);
        System.out.println(gson.toJson(answer));
    }
}