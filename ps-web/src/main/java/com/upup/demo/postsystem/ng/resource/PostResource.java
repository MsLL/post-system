package com.upup.demo.postsystem.ng.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author tao.li
 * @Date 2021/1/25 下午8:49
 */
@Controller
@ResponseBody
@RequestMapping("/post")
public class PostResource {
    @RequestMapping(value = "/ping",method = RequestMethod.GET)
    public String echoOk() {
        return "pong";
    }
}
