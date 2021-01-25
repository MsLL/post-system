package com.upup.demo.postsystem.ng.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author tao.li
 * @Date 2021/1/25 下午10:01
 */
@Controller
@ResponseBody
@RequestMapping("/answer")
public class AnswerResource {
    @RequestMapping(value = "/ping",method = RequestMethod.GET)
    public String delayOk() throws InterruptedException {
        Thread.sleep(5*1000);
        return "pong";
    }
}
