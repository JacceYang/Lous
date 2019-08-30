package com.iyetoo.mpm.lous.cache.ext;

import com.iyetoo.mpm.lous.cache.Cache;
import com.iyetoo.mpm.lous.cache.CacheManager;
import com.iyetoo.mpm.lous.cache.annotation.CachingMode;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 5:40 PM 2019/8/11
 **/
@Component
public class LocalCacheManager implements CacheManager {

    Map<String, Cache> cacheManger = new HashMap<>();

    @Override
    public Cache getCache(String cacheName) {
        Cache cache = cacheManger.get(cacheName);
        if (cache == null) {
            cacheManger.put(cacheName, new LocalCache());
        }
        return cacheManger.get(cacheName);
    }

    @Override
    public Set<String> cacheNames() {
        return cacheManger.keySet();
    }

    @Override
    public Integer getCacheLine() {
        return CachingMode.LOCAL.getCacheLine();
    }
}
