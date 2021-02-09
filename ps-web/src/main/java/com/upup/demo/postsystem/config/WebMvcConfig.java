package com.upup.demo.postsystem.config;

import com.upup.demo.postsystem.interceptor.LoginInterceptor;
import com.upup.demo.postsystem.interceptor.OperationPermissionCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author tao.li
 * @Date 2021/1/27 上午12:54
 */
@Component
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor loginInterceptor;

    @Autowired
    OperationPermissionCheckInterceptor operationPermissionCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns(
                        "/ping",
                        "/listBeans",
                        "/parse",
                        "/user/login",
                        "/post/ping",
                        "/answer/ping",
                        "/comment/ping",
                        "/qrcode",

//swagger bypass: swagger-ui.html appears to be empty    https://github.com/springfox/springfox/issues/2093
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/swagger-resources/**",
                        "/v2/api-docs"
                )
                .order(0);

        registry.addInterceptor(operationPermissionCheckInterceptor)
                .order(1);
    }
}
