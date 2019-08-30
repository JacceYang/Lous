package com.iyetoo.mpm.lous.cache;

import java.util.Set;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 5:36 PM 2019/8/11
 **/
public interface CacheManager {

    /**
     * @param cacheName
     * @return
     */
    Cache getCache(String cacheName);

    /**
     * @return
     */
    Set<String> cacheNames();


    Integer getCacheLine();
}
