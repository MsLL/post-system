package com.upup.demo.postsystem.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author tao.li
 * @Date 2021/1/26 下午11:14
 * 记录登陆登出log，具体包括登陆ip,时间，登陆成功与否，失败原因。
 */
@Aspect
@Component
public class LoginLogoutAspect {
    private Logger logger= LoggerFactory.getLogger(LoginLogoutAspect.class);

    @Pointcut("execution(public * com.upup.demo.postsystem.user.UserResource.login(..))"
        + "|| execution(public * com.upup.demo.postsystem.user.UserResource.logout(..))")
    public void pointCuts() {
    }

    @Before("pointCuts()")
    public void doBefore(JoinPoint joinPoint) {
        //log login ip,login time
        logger.info("enter LoginLogoutAspect before method");
    }

    @AfterReturning("pointCuts()")
    public void doAfterReturning() {
        //log login result suecess || fail
        logger.info("enter LoginLogoutAspect after method");
    }

    @AfterThrowing(value = "pointCuts()", throwing = "throwable")
    public void doAfterThrowing(Throwable throwable) {
    }
}
