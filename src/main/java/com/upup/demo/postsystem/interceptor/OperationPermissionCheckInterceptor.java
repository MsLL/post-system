package com.upup.demo.postsystem.interceptor;

import com.upup.demo.postsystem.annotation.Permission;
import java.lang.reflect.Method;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Author tao.li
 * @Date 2021/1/28 上午12:18
 */
@Order(2) //should keep priority  lower than LoginInterceptor logically。
@Component
@Slf4j
public class OperationPermissionCheckInterceptor implements HandlerInterceptor {
    @Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //notice : handel may not always a HandlerMethod,for example if no controller's handler match path,this might be null
        if (handler instanceof HandlerMethod) {
            Method method = ((HandlerMethod) handler).getMethod();
            if (method.isAnnotationPresent(Permission.class)) {
                Permission permission = method.getDeclaredAnnotation(Permission.class);
                int[] permissionCodes = permission.value();
                log.info(Arrays.toString(permissionCodes));
                //todo check if user has permissionCodes
                return true;
            }
        }
        return true;
    }
}
