package com.upup.demo.postsystem.bss.user.service.auth;

import com.upup.demo.postsystem.bss.user.model.auth.AuthenticateModel;
import com.upup.demo.postsystem.bss.user.model.auth.AuthenticateResult;
import com.upup.demo.postsystem.bss.user.model.auth.UsernamePasswordAuthenticateModel;

/**
 * @Author tao.li
 * @Date 2021/1/28 上午4:00
 */
public class UsernamePasswordAuthenticateService implements AuthenticateService {

    @Override public AuthenticateResult authenticate(AuthenticateModel authenticateModel) {
        UsernamePasswordAuthenticateModel usernamePasswordAuthenticateModel=(UsernamePasswordAuthenticateModel) authenticateModel;
        if("1A中文".equals(usernamePasswordAuthenticateModel.getUsername())){
            return new AuthenticateResult(true,null);
        }
        return new AuthenticateResult(false,"only support user 1A中文 login");
    }
}
