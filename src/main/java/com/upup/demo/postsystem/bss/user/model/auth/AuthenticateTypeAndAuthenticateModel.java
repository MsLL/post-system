package com.upup.demo.postsystem.bss.user.model.auth;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.upup.demo.postsystem.bss.user.Constants;
import com.upup.demo.postsystem.bss.user.enums.AuthenticateType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author tao.li
 * @Date 2021/1/28 上午3:42
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticateTypeAndAuthenticateModel {
    public static final AuthenticateTypeAndAuthenticateModel getAuthenticateTypeAndAuthenticateModel(String jsonData) {
        JsonObject jsonObject = JsonParser.parseString(jsonData).getAsJsonObject();
        AuthenticateTypeAndAuthenticateModel authenticateTypeAndAuthenticateModel = new AuthenticateTypeAndAuthenticateModel();
        switch (jsonObject.get(Constants.AUTH_TYPE).getAsInt()) {
            case Constants.USERNAME_PASSWORD_AUTH: {
                AuthenticateModel authenticateModel =
                    new UsernamePasswordAuthenticateModel(jsonObject.get("username").getAsString(), jsonObject.get("password").getAsString());

                authenticateTypeAndAuthenticateModel.setAuthenticateType(AuthenticateType.UsernamePasswordAuthenticate);
                authenticateTypeAndAuthenticateModel.setAuthenticateModel(authenticateModel);
                break;
            }
            case Constants.PHONEMUMBER_VERFYCODE_AUTH: {
                break;
            }
            case Constants.OAUTH_AUTH: {
                break;
            }
            case Constants.APPKEY_APPSECRET__AUTH: {
                break;
            }
            default:
                throw new RuntimeException("authType not supported yet, authInfo :" + jsonData);
        }
        return authenticateTypeAndAuthenticateModel;
    }

    private AuthenticateType authenticateType;
    private AuthenticateModel authenticateModel;
}
