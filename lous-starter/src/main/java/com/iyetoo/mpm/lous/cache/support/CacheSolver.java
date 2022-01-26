package com.iyetoo.mpm.lous.cache.support;

import com.iyetoo.mpm.lous.cache.Cache;
import com.iyetoo.mpm.lous.cache.CacheManager;

import java.util.List;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 5:43 PM 2019/8/11
 **/
public interface CacheSolver {

    List<Cache> determineUltimateCache(List<CacheManager> cacheManagers, String cacheName);
}
