package com.upup.demo.postsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author tao.li
 * @Date 2021/1/27 下午12:29 yes controller, not resource
 */
@Controller
@ResponseBody
public class PostSystemController {
    @Autowired
    ApplicationContext applicationContext;

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping() {
        return "pong";
    }

    //todo add permission check
    //https://stackoverflow.com/questions/14423980/how-to-close-a-spring-applicationcontext
    @RequestMapping(value = "/shutdown", method = RequestMethod.GET)
    public void stop() {
        ((ConfigurableApplicationContext) applicationContext).close();
    }
}
