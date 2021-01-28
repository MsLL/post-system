package com.upup.demo.postsystem.interceptor;

import java.io.PrintWriter;
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
//@Order(1) 不需要这么写了，比较分散，与excludePaths一样，在register拦截器的时候去指定order,这样更集中。

@Component
public class LoginInterceptor implements HandlerInterceptor {
    private Logger logger= LoggerFactory.getLogger(LoginInterceptor.class);

    @Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //todo add check
        PrintWriter writer = response.getWriter();
        writer.println("please login first");
        writer.flush();
        return false;

    }

    @Override public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
        throws Exception {
    }

    @Override public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
