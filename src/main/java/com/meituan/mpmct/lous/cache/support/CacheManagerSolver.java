package com.meituan.mpmct.lous.cache.support;

import com.meituan.mpmct.lous.cache.CacheManager;
import com.meituan.mpmct.lous.cache.annotation.CachingMode;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 5:42 PM 2019/8/11
 **/
public interface CacheManagerSolver {

    List<CacheManager> getCacheManager(LinkedHashSet<CachingMode> modes);

}
