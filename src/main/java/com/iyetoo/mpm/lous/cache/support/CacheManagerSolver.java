package com.iyetoo.mpm.lous.cache.support;

import com.iyetoo.mpm.lous.cache.CacheManager;
import com.iyetoo.mpm.lous.cache.annotation.CachingMode;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 5:42 PM 2019/8/11
 **/
public interface CacheManagerSolver {

    List<CacheManager> getCacheManager(LinkedHashSet<CachingMode> modes);

}
