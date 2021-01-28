package com.upup.demo.postsystem.bss.user.service;

import com.upup.demo.postsystem.bss.user.model.AuthenticateModel;
import com.upup.demo.postsystem.bss.user.model.AuthenticateResult;
import com.upup.demo.postsystem.bss.user.model.UsernamePasswordAuthenticateModel;

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
