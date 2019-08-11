package com.meituan.mpmct.lous.cache;

import java.util.Set;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 5:36 PM 2019/8/11
 **/
public interface CacheManager {

    /**
     *
     * @param cacheName
     * @return
     */
     Cache getCache(String cacheName);

    /**
     *
     * @return
     */
    Set<String> cacheNames();
}
