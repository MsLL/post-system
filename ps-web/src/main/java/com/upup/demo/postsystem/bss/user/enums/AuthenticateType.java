package com.upup.demo.postsystem.bss.user.enums;

import com.upup.demo.postsystem.bss.user.Constants;

/**
 * @Author tao.li
 * @Date 2021/1/28 上午3:22
 */
public enum AuthenticateType {
    UsernamePasswordAuthenticate(Constants.USERNAME_PASSWORD_AUTH),
    PhonenumberVerfycodeAuthenticate(Constants.PHONEMUMBER_VERFYCODE_AUTH),
    OAuthAuthenticate(Constants.OAUTH_AUTH),
    AppkeyAppsecretAuthenticate(Constants.APPKEY_APPSECRET__AUTH);

    private final int authType;

    //in enum class, construct is default private
    AuthenticateType(int authType) {
        this.authType = authType;
    }
}
