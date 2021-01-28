package com.upup.demo.postsystem.bss.user;

import com.upup.demo.postsystem.bss.user.model.AuthenticateResult;
import com.upup.demo.postsystem.bss.user.service.AuthenticateServiceFactory;
import com.upup.demo.postsystem.bss.user.service.AuthenticateTypeAndAuthenticateModel;
import com.upup.demo.postsystem.model.ResourceResponseModel;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResourceResponseModel login(@RequestBody String jsonData) {
        AuthenticateTypeAndAuthenticateModel authenticateTypeAndAuthenticateModel =
            AuthenticateTypeAndAuthenticateModel.getAuthenticateTypeAndAuthenticateModel(jsonData);
        AuthenticateResult authenticateResult =
            AuthenticateServiceFactory.getAuthenticateService(authenticateTypeAndAuthenticateModel.getAuthenticateType())
                .authenticate(authenticateTypeAndAuthenticateModel.getAuthenticateModel());
        if (authenticateResult.isSuccess()) {
            //todo store session info
            return ResourceResponseModel.builder().code(200).data("login success").build();
        } else {
            return ResourceResponseModel.builder().code(200).data("login fail for " + authenticateResult.getFailReason().toString()).build();
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    public ResourceResponseModel logout() {
        return ResourceResponseModel.builder().code(200).data("logout success").build();
    }
}
