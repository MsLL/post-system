package com.upup.demo.postsystem.bss.user.service;

import com.upup.demo.postsystem.dictionary.Constants;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

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
        stringRedisTemplate.expireAt(pserId, new Date(System.currentTimeMillis() + Constants.DEFAULT_COOKIE_MAX_AGE * (long)1000));
    }

    public void logoutUser(String pserId) {
        stringRedisTemplate.delete(pserId);
    }


}
