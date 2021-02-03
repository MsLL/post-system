package com.upup.demo.postsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;


/**
 * imitate org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
 * 1.copy org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration content,模仿它
 *  org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration的@bean方法有ConditionalOnMissingBean注解，由于用户bean优先，所以我们的配置会优先生效
 * 2.我们需要往容器中加入一个RedisConnectionFactory的实例，否则ConditionalOnSingleCandidate不成立，org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration那边没有注入一个默认的redisConnectionFactory
 * 3.该版本spring-data-redis自己引入的lettuce-core包有问题，实例化LettuceConnectionFactory的时候回报错(刚好这里用的LettuceConnectionFactory)，pom里exclude后引入了一个高级的版本
 */
@Configuration
public class RedisAutoConfiguration {

    @Autowired RedisProperties redisProperties;

    //NOTE-UPUP 2021/1/29 上午2:07 : 我们需要往容器中加入一个RedisConnectionFactory的实例，否则下面的ConditionalOnSingleCandidate不成立
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        return new LettuceConnectionFactory(redisProperties.getHost(), redisProperties.getPort());
    }

    //see https://developer.ibm.com/zh/articles/know-redis-and-use-it-in-springboot-projects/

    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    @ConditionalOnSingleCandidate(RedisConnectionFactory.class)
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnSingleCandidate(RedisConnectionFactory.class)
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }



}
