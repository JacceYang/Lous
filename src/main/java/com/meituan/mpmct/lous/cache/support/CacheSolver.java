package com.meituan.mpmct.lous.cache.support;

import com.meituan.mpmct.lous.cache.Cache;
import com.meituan.mpmct.lous.cache.CacheManager;

import java.util.List;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 5:43 PM 2019/8/11
 **/
public interface CacheSolver {

    List<Cache> determineUltimateCache(List<CacheManager> cacheManagers, String cacheName);
}
