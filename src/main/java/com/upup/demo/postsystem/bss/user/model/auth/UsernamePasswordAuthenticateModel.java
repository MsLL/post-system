package com.upup.demo.postsystem.bss.user.model.auth;

import com.upup.demo.postsystem.bss.user.model.auth.AuthenticateModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author tao.li
 * @Date 2021/1/28 上午3:15
 */
@Getter
@Setter
@AllArgsConstructor
public class UsernamePasswordAuthenticateModel extends AuthenticateModel {
    private String username;
    private String password;
}
