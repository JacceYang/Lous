package com.iyetoo.mpm.lous.cache.support;

import com.iyetoo.mpm.lous.cache.Cache;
import com.iyetoo.mpm.lous.cache.CacheManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 5:49 PM 2019/8/11
 **/
public class CacheSolverSupport implements CacheSolver {

    @Override
    public List<Cache> determineUltimateCache(List<CacheManager> cacheManagers, String cacheName) {
        List<Cache> result = new ArrayList<>();
        for (CacheManager cacheManager : cacheManagers) {
            Cache cache = cacheManager.getCache(cacheName);
            if (cache != null) {
                result.add(cache);
            }
        }
        return result;
    }
}
