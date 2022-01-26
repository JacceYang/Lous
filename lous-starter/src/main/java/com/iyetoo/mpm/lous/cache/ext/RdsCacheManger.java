package com.iyetoo.mpm.lous.cache.ext;

import com.iyetoo.mpm.lous.cache.Cache;
import com.iyetoo.mpm.lous.cache.CacheManager;
import com.iyetoo.mpm.lous.cache.annotation.CachingMode;

import java.util.Set;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 5:40 PM 2019/8/11
 **/
public class RdsCacheManger implements CacheManager {


    @Override
    public Cache getCache(String cacheName) {
        return null;
    }

    @Override
    public Set<String> cacheNames() {
        return null;
    }

    @Override
    public Integer getCacheLine() {
        return CachingMode.REDIS.getCacheLine();
    }
}
