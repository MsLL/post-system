package com.upup.demo.postsystem.aspect;

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

/**
 * @Author tao.li
 * @Date 2021/1/25 下午9:08 统计接口耗时--will save to db
 */
@Aspect
@Component
public class ApiTimeConsumeAspect {
    private static ThreadLocal<ApiTimeConsumeModel> threadLocal = new ThreadLocal<>();
    private Logger logger = LoggerFactory.getLogger(ApiTimeConsumeAspect.class);

    @Pointcut("within(com.upup.demo.postsystem.ng.resource.PostResource)"
        + "|| within(com.upup.demo.postsystem.ng.resource.AnswerResource)"
        + "|| within(com.upup.demo.postsystem.ng.resource.CommentResource)")
    public void pointCuts() {
    }

    @Before("pointCuts()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        Object[] args = joinPoint.getArgs();

        ApiTimeConsumeModel apiTimeConsumeModel = ApiTimeConsumeModel.builder(startTime, methodName, className, args);
        threadLocal.set(apiTimeConsumeModel);
    }

    @AfterReturning("pointCuts()")
    public void doAfterReturning() {
        long endTime = System.currentTimeMillis();
        ApiTimeConsumeModel apiTimeConsumeModel = threadLocal.get();
        logger.info("method {} in {} spend {} millis with args {}", apiTimeConsumeModel.getClassName(), apiTimeConsumeModel.getMethodName(),
            endTime - apiTimeConsumeModel.getStartTime(), apiTimeConsumeModel.getArgs());

        threadLocal.remove();
    }

    @AfterThrowing(value = "pointCuts()", throwing = "throwable")
    public void doAfterThrowing(Throwable throwable) {
        logger.error("{}", threadLocal.get());
        logger.error("api error", throwable);
        threadLocal.remove();
    }
}

@Getter
@Setter
class ApiTimeConsumeModel {
    private long startTime;
    private String className;
    private String methodName;
    private Object[] args;

    public static ApiTimeConsumeModel builder(long startTime, String className, String methodName, Object[] args) {
        ApiTimeConsumeModel apiTimeConsumeModel = new ApiTimeConsumeModel();
        apiTimeConsumeModel.setStartTime(startTime);
        apiTimeConsumeModel.setClassName(className);
        apiTimeConsumeModel.setMethodName(methodName);
        apiTimeConsumeModel.setArgs(args);
        return apiTimeConsumeModel;
    }
}