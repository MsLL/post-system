package com.upup.demo.postsystem.exception;

/**
 * 
 * @Date 2021/1/27 上午3:26
 */
public class BizException extends RuntimeException {
    public BizException() {
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }
}
