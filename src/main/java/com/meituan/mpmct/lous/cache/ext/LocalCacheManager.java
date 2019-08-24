package com.meituan.mpmct.lous.cache.ext;

import com.meituan.mpmct.lous.cache.Cache;
import com.meituan.mpmct.lous.cache.CacheManager;
import com.meituan.mpmct.lous.cache.annotation.CachingMode;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 5:40 PM 2019/8/11
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
