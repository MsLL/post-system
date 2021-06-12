package com.upup.demo.postsystem.config;

import com.upup.demo.postsystem.interceptor.LoginInterceptor;
import com.upup.demo.postsystem.interceptor.OperationPermissionCheckInterceptor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
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
                        "/listClassPath",
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

    @Override public void addFormatters(FormatterRegistry registry) {
        //NOTE-UPUP 2021/3/20 下午9:24 :  为请求参数定义java.lang.String 到 java.util.Date的转换器。 https://blog.csdn.net/weixin_31479991/article/details/112575022
        registry.addConverter(stringToDateConvert());
    }


    private Converter stringToDateConvert(){
        return new StringToDateConvert();
    }


}

/**
 *
 * todo : yyyy-MM-dd cause exception,for example 2021-03-18
 */
class StringToDateConvert implements Converter<String, Date> {

    @Override public Date convert(String source) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(source);
        } catch (ParseException e) {
            throw  new RuntimeException(e);
        }
    }

}
