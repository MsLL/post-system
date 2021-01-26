package com.upup.demo.postsystem.user;

import com.upup.demo.postsystem.model.ResourceResponseModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author tao.li
 * @Date 2021/1/26 下午11:15 this module only a Resource class ,so not create a package like ng/resource
 */
@Controller
@ResponseBody
@RequestMapping("/user")
public class UserResource {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResourceResponseModel login() {
        return ResourceResponseModel.builder().code(200).data("login success").build();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    public ResourceResponseModel logout() {
        return ResourceResponseModel.builder().code(200).data("logout success").build();
    }
}
