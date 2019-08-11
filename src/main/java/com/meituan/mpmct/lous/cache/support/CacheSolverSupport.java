package com.meituan.mpmct.lous.cache.support;

import com.meituan.mpmct.lous.cache.Cache;
import com.meituan.mpmct.lous.cache.CacheManager;

import java.util.List;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 5:49 PM 2019/8/11
 **/
public class CacheSolverSupport implements CacheSolver{

    @Override
    public Cache determineUltimateCache(List<CacheManager> cacheManagers, String cacheName) {
        return null;
    }
}
