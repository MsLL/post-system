package com.upup.demo.postsystem.util;

import com.google.common.base.Charsets;
import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @Date 2021/4/8 下午11:06
 */

@Component
public class PostSystemConfig {
    Logger logger = LoggerFactory.getLogger(PostSystemConfig.class);
    @Autowired CuratorFramework client;

    public String getValue(String key) {
        try {
            byte[] data = client.getData().forPath(key);
            return new String(data, Charsets.UTF_8);
        } catch (Exception e) {
            logger.error("", e);
            throw new RuntimeException(e);
        }
    }
}
