package com.upup.demo.postsystem.bss.user.service;

import com.upup.demo.postsystem.bss.user.enums.AuthenticateType;

/**
 * @Author tao.li
 * @Date 2021/1/28 上午3:20
 */
public class AuthenticateServiceFactory {
    public static final AuthenticateService getAuthenticateService(AuthenticateType authenticateType) {
        switch (authenticateType) {
            case UsernamePasswordAuthenticate:
                return new UsernamePasswordAuthenticateService();
            //...
            default:
                throw new RuntimeException("Authenticate not support yet");
        }
    }
}
