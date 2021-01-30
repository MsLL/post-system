package com.upup.demo.postsystem.bss.user.service;

import com.upup.demo.postsystem.bss.user.model.UserModel;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author tao.li
 * @Date 2021/1/30 下午5:27
 */
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void test0(){
        Optional<UserModel> optional=userService.findByUserName("A1");
        System.out.println(optional.toString());
    }
}