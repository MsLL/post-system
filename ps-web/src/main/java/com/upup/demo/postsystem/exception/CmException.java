package com.upup.demo.postsystem.exception;

import java.awt.ScrollPane;
import java.awt.color.CMMException;

/**
 * 
 * @Date 2021/1/27 上午3:29
 */
public class CmException extends BizException {
    public CmException() {
    };

    public CmException(String message) {
        super(message);
    };

    public CmException(String message, Throwable cause) {
        super(message, cause);
    }
}
