package com.upup.demo.postsystem.exception;

/**
 * 
 * @Date 2021/1/27 上午3:28
 */
public class HdfsException extends BizException {
    public HdfsException(){};
    public HdfsException(String message){
        super(message);
    }
    public HdfsException(String message,Throwable cause){
        super(message,cause);
    }
}
