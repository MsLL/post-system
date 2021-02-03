package com.upup.demo.postsystem.bss.user.service.auth;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.upup.demo.postsystem.PostSystemApplication;
import com.upup.demo.postsystem.bss.user.model.UserModel;
import com.upup.demo.postsystem.bss.user.model.auth.AuthenticateModel;
import com.upup.demo.postsystem.bss.user.model.auth.AuthenticateResult;
import com.upup.demo.postsystem.bss.user.model.auth.UsernamePasswordAuthenticateModel;
import com.upup.demo.postsystem.bss.user.service.UserService;
import java.util.Optional;

/**
 * @Author tao.li
 * @Date 2021/1/28 上午4:00
 */
//not need to add to spring container and notice AuthenticateServiceFactory use new keyword
public class UsernamePasswordAuthenticateService implements AuthenticateService {

    private UserService userService = PostSystemApplication.getApplicationContext().getBean(UserService.class);

    @Override public AuthenticateResult authenticate(AuthenticateModel authenticateModel) {
        UsernamePasswordAuthenticateModel usernamePasswordAuthenticateModel = (UsernamePasswordAuthenticateModel) authenticateModel;
        String username = usernamePasswordAuthenticateModel.getUsername();
        String password = usernamePasswordAuthenticateModel.getPassword();

        Optional<UserModel> user = userService.findByUserName(username);
        if (!user.isPresent()) {
            return AuthenticateResult.builder()
                .success(false)
                .failReason("no user: " + username)
                .build();
        }
        String salt = user.get().getSalt();
        String digestedPass = new Digester(DigestAlgorithm.MD5).digestHex(salt + password);
        if (userService.varifyPassword(user.get().getId(), digestedPass)) {
            return AuthenticateResult.builder()
                .success(true)
                .successData(user.get())
                .build();
        } else {
            return AuthenticateResult.builder()
                .success(false)
                .failReason("password error")
                .build();
        }
    }
}
