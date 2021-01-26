package com.upup.demo.postsystem.interceptor;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author tao.li
 * @Date 2021/1/26 下午11:48 拦截掉未登陆的用户(没有约定的header || cookie里面没有约定的key, 或者值解码不是一个系统用户)。login api,ping apis放行。 see
 * https://segmentfault.com/a/1190000015642264
 */
//NOTE-UPUP 2021/1/27 上午1:16 : 拦截器光加入容器是不生效的，还要注册，见WebMvcConfig
//NOTE-UPUP 2021/1/27 上午1:16 : The value() is optional and represents an order value as defined in the Ordered interface. Lower values have higher priority. The default value is Ordered

@Order(1)
@Component
public class LoginInterceptor implements HandlerInterceptor {
    private Logger logger= LoggerFactory.getLogger(LoginInterceptor.class);
    private static List<String> excludePaths=new ArrayList<>();
    static {
        excludePaths.add("/user/login");
        excludePaths.add("/post/ping");
        excludePaths.add("/answer/ping");
        excludePaths.add("/comment/ping");
    }

    @Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("enter LoginInterceptor preHandle");
        //NOTE-UPUP 2021/1/27 上午1:21 : 获取请求的RUi,去除http:localhost:8080这部分剩下的。也就是域名后面的path部分
        String uri=request.getRequestURI();
        if(excludePaths.stream().anyMatch((excludePath)-> uri.equals(excludePath))){
            return true;
        }
        //todo add check
        return true;

    }

    @Override public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
        throws Exception {
        logger.info("enter LoginInterceptor postHandle");
    }

    @Override public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("enter LoginInterceptor afterCompletion");

    }
}
