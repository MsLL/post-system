package com.upup.demo.postsystem.bss.resource;

import com.upup.demo.postsystem.bss.user.model.auth.AuthenticateResult;
import com.upup.demo.postsystem.bss.user.service.UserService;
import com.upup.demo.postsystem.bss.user.service.auth.AuthenticateServiceFactory;
import com.upup.demo.postsystem.bss.user.model.auth.AuthenticateTypeAndAuthenticateModel;
import com.upup.demo.postsystem.dictionary.Constants;
import com.upup.demo.postsystem.model.ResourceResponseModel;
import com.upup.demo.postsystem.util.WebUtil;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @Author tao.li
 * @Date 2021/1/26 下午11:15 this module only a Resource class ,so not create a package like ng/resource
 */
@Controller
@ResponseBody
@RequestMapping("/user")
public class UserResource {

    @Autowired UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResourceResponseModel login(@RequestBody String jsonData, HttpServletResponse response) {
        AuthenticateTypeAndAuthenticateModel authenticateTypeAndAuthenticateModel =
            AuthenticateTypeAndAuthenticateModel.getAuthenticateTypeAndAuthenticateModel(jsonData);
        AuthenticateResult authenticateResult =
            AuthenticateServiceFactory.getAuthenticateService(authenticateTypeAndAuthenticateModel.getAuthenticateType())
                .authenticate(authenticateTypeAndAuthenticateModel.getAuthenticateModel());
        if (authenticateResult.isSuccess()) {
            String pserId = WebUtil.generatePserId();
            userService.loginUser(WebUtil.getPserId(pserId), authenticateResult.getSuccessData());
            WebUtil.addCookie(response, Constants.PSER_KEY, pserId);

            return ResourceResponseModel.builder().code(200).data("login success").build();
        } else {
            return ResourceResponseModel.builder().code(200).data("login fail for " + authenticateResult.getFailReason().toString()).build();
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    public ResourceResponseModel logout(HttpServletResponse response) {
        String pserId = RequestContextHolder.getRequestAttributes().getAttribute(Constants.PSER_KEY, RequestAttributes.SCOPE_REQUEST).toString();
        userService.logoutUser(pserId);
        WebUtil.deleteCookie(response, Constants.PSER_KEY);
        return ResourceResponseModel.builder().code(200).data("logout success").build();
    }
}
