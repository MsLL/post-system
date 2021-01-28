package com.upup.demo.postsystem.bss.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author tao.li
 * @Date 2021/1/28 上午3:31
 */
@Getter
@Setter
@AllArgsConstructor
public class AuthenticateResult{
    private boolean success;
    private Object failReason;
}
