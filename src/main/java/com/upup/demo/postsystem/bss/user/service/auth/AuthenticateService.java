package com.upup.demo.postsystem.bss.user.service.auth;

import com.upup.demo.postsystem.bss.user.model.AuthenticateModel;
import com.upup.demo.postsystem.bss.user.model.AuthenticateResult;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author tao.li
 * @Date 2021/1/28 上午3:21
 */
public interface AuthenticateService {
    AuthenticateResult authenticate(AuthenticateModel authenticateModel);


}

