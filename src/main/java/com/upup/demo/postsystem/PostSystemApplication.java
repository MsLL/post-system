package com.upup.demo.postsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PostSystemApplication {

    private static ApplicationContext applicationContext = null;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(PostSystemApplication.class, args);
        applicationContext = context;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
