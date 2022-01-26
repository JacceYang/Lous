package com.iyetoo.mpm.lous.keep.duplix.ext;

import com.iyetoo.mpm.lous.keep.duplix.support.MemCache;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

@Component
@ConditionalOnProperty(prefix = "lous",name = "duplix.redis.client")
public class RedisMemCache implements MemCache {

    @Resource
    @Qualifier("@{lous.duplix.redis.client:\"\"}")
    private Jedis redisClient;

    @Override
    public void putCache(Object key, String value, long ms, int times) {

    }

    @Override
    public String getCache(Object key) {
        return null;
    }
}
