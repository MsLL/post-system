package com.upup.demo.postsystem.bss.user.model.auth;

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
@Builder
public class AuthenticateResult{
    private boolean success;
    private Object failReason;
    private Object successData;
}
