package com.upup.demo.postsystem.ng.answer.dao;

import com.upup.demo.postsystem.H2Config;
import com.upup.demo.postsystem.ng.answer.po.Answer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @Date 2021/6/12 12:42
 */

//for junit5
@ExtendWith(SpringExtension.class)

@ContextConfiguration(classes = {H2Config.class, MybatisAutoConfiguration.class,})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@ActiveProfiles({"dev"})
@MapperScan("com.upup.demo.postsystem.ng.answer.dao")
class AnswerDaoTest {

    @Autowired
    private AnswerDao answerDao;

    private final int postId = 1;
    private final int userId = 1;

    //todo org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): com.upup.demo.postsystem.ng.answer.dao.AnswerDao.insert
    //debug到org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration类，传入的MybatisProperties实例的所有字段都是null.
    @Order(0)
    @Test
    void insert() {
        Answer answer = Answer.builder()
                .postId(postId)
                .userId(userId)
                .content("content")
                .state("s")
                .createDatetime(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .updateDatetime(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .build();
        answerDao.insert(answer);
        Assertions.assertNotNull(answer.getId());
    }

    @Test
    void findById() {
    }

    @Test
    void list() {
    }

    @Test
    void update() {
    }

    @Order(9999)
    @Test
    void deleteById() {

    }
}