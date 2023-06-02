package com.ohh.icloud.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@Component
public class RedisUtil {

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 设置string类型带有过期时间的
     * @param key key
     * @param value value
     * @param expire 过期时间
     * @param unit 时间单位
     */
    public void setStringRedisWithExpire(String key, String value, Long expire, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, expire, unit);
    }

}
