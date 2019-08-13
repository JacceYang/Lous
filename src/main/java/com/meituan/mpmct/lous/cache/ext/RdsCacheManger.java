package com.meituan.mpmct.lous.cache.ext;

import com.meituan.mpmct.lous.cache.Cache;
import com.meituan.mpmct.lous.cache.CacheManager;
import com.meituan.mpmct.lous.cache.annotation.CachingMode;

import java.util.Set;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 5:40 PM 2019/8/11
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
