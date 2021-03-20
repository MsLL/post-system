package com.upup.demo.postsystem.aspect;

import com.google.gson.Gson;
import com.upup.demo.postsystem.util.WebUtil;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Author tao.li
 * @Date 2021/1/26 下午11:14 记录登陆登出log，具体包括登陆ip,时间，登陆成功与否，失败原因。
 */
@Aspect
@Component
public class LoginLogoutAspect {
    private Logger logger = LoggerFactory.getLogger(LoginLogoutAspect.class);
    ThreadLocal<LoginLogoutModel> threadLocal = new ThreadLocal<>();

    @Pointcut("execution(public * com.upup.demo.postsystem.bss.resource.UserResource.login(..))"
        + "|| execution(public * com.upup.demo.postsystem.bss.resource.UserResource.logout(..))")
    public void pointCuts() {
    }

    @Before("pointCuts()")
    public void doBefore(JoinPoint joinPoint) {
        Date date = new Date(System.currentTimeMillis());
        String type = joinPoint.getSignature().getName();//login or logout

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String httpMethod = servletRequestAttributes.getRequest().getMethod();
        String ip = WebUtil.getIpAddr(servletRequestAttributes.getRequest());
        LoginLogoutModel loginLogoutModel = LoginLogoutModel.loginLogoutModelBuild(ip, date, type, httpMethod, null);
        threadLocal.set(loginLogoutModel);
    }

    @AfterReturning(value = "pointCuts()", returning = "returnValue")
    public void doAfterReturning(JoinPoint joinPoint, Object returnValue) {
        LoginLogoutModel loginLogoutModel = threadLocal.get();
        loginLogoutModel.setResult(returnValue);
        logger.info("{} operation record: {}", loginLogoutModel.getType(), new Gson().toJson(loginLogoutModel));
        threadLocal.remove();
    }

    @AfterThrowing(value = "pointCuts()", throwing = "throwable")
    public void doAfterThrowing(Throwable throwable) {
        threadLocal.remove();
    }

}

@Getter
@Setter
class LoginLogoutModel {
    private String ip;
    private Date date;
    private String httpMethod;
    private String type;//login or logout
    private Object result;

    public static LoginLogoutModel loginLogoutModelBuild(String ip, Date date, String type, String httpMethod, String result) {
        LoginLogoutModel loginLogoutModel = new LoginLogoutModel();
        loginLogoutModel.ip = ip;
        loginLogoutModel.date = date;
        loginLogoutModel.type = type;
        loginLogoutModel.httpMethod = httpMethod;
        loginLogoutModel.result = result;
        return loginLogoutModel;
    }
}