package com.upup.demo.postsystem.bss.user.service;

import com.upup.demo.postsystem.dictionary.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @Author tao.li
 * @Date 2021/1/30 上午1:05
 */
@Component
public class UserService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * check whether user has login post system
     */
    public boolean hasUserLogin(String pserId) {
        if (!pserId.startsWith(Constants.PSER_KEY) || !stringRedisTemplate.hasKey(pserId)) {
            return false;
        }
        return true;
    }

    public void loginUser(String pserId) {
        //todo: add info
        stringRedisTemplate.opsForValue().set(pserId, "{}");
        //后续处理请求的时候可能还要拿这个pserId
        RequestContextHolder.getRequestAttributes().setAttribute(Constants.PSER_KEY, pserId, RequestAttributes.SCOPE_REQUEST);
    }

    public void logoutUser(String pserId) {
        stringRedisTemplate.delete(pserId);
    }
}
