package com.upup.demo.postsystem.bss.user.service.auth;

import com.upup.demo.postsystem.bss.user.model.auth.AuthenticateModel;
import com.upup.demo.postsystem.bss.user.model.auth.AuthenticateResult;

/**
 * 
 * @Date 2021/1/28 上午3:21
 */
public interface AuthenticateService {
    AuthenticateResult authenticate(AuthenticateModel authenticateModel);


}

