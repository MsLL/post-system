package com.upup.demo.postsystem.exceptionhandler;

import com.upup.demo.postsystem.exception.BizException;
import com.upup.demo.postsystem.model.ResourceResponseModel;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author tao.li
 * @Date 2021/1/27 上午3:07 see :https://www.cnblogs.com/xuwujing/p/10933082.html
 */
//NOTE-UPUP 2021/1/27 上午3:07 : 全局异常处理
//NOTE-UPUP 2021/1/27 上午3:44 : ExceptionHandler的执行顺序 https://juejin.cn/post/6844903865054150669
//todo learn about this annotation

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //BizException class and subclass
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public ResourceResponseModel bizExceptionHandler(HttpServletRequest req, BizException e) {
        logger.error("发生异常！" + e.getMessage(), e);
        return ResourceResponseModel.builder()
            .code(500)
            .data(e.getMessage())
            .build();
    }

    /**
     *处理空指针的异常
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResourceResponseModel nullPointExceptionHandler(HttpServletRequest req, NullPointerException e) {
        logger.error("发生异常！" + e.getMessage(), e);
        return ResourceResponseModel.builder()
            .code(500)
            .data(e.getMessage())
            .build();
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ResourceResponseModel runtimeExceptionHandler(HttpServletRequest req, RuntimeException e) {
        logger.error("发生异常！" + e.getMessage(), e);
        return ResourceResponseModel.builder()
            .code(500)
            .data(e.getMessage())
            .build();
    }


    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public  ResourceResponseModel exceptionHandler(HttpServletRequest req, Exception e){
        logger.error("发生异常！" + e.getMessage(), e);
        return ResourceResponseModel.builder()
            .code(500)
            .data(e.getMessage())
            .build();
    }

}
