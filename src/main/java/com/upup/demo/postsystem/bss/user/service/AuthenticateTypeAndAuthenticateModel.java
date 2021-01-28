package com.upup.demo.postsystem.bss.user.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.upup.demo.postsystem.bss.user.Constants;
import com.upup.demo.postsystem.bss.user.enums.AuthenticateType;
import com.upup.demo.postsystem.bss.user.model.AuthenticateModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author tao.li
 * @Date 2021/1/28 上午3:42
 */
@Getter
@Setter
@Builder
public class AuthenticateTypeAndAuthenticateModel {
    public static final AuthenticateTypeAndAuthenticateModel getAuthenticateTypeAndAuthenticateModel(String jsonData) {
        JsonObject jsonObject = JsonParser.parseString(jsonData).getAsJsonObject();
        switch (jsonObject.get(Constants.AUTH_TYPE).getAsInt()) {
            case Constants.USERNAME_PASSWORD_AUTH: {
            }
            ;
            case Constants.PHONEMUMBER_VERFYCODE_AUTH: {

            }
            ;
            case Constants.OAUTH_AUTH: {
            }
            ;
            case Constants.APPKEY_APPSECRET__AUTH: {
            }
            ;
            default:
                throw new RuntimeException("authType not supported yet, authInfo :" + jsonData);
        }
    }

    private AuthenticateType authenticateType;
    private AuthenticateModel authenticateModel;
}
