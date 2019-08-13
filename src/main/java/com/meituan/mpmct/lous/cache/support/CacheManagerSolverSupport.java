package com.meituan.mpmct.lous.cache.support;

import com.meituan.mpmct.lous.cache.CacheManager;
import com.meituan.mpmct.lous.cache.annotation.CachingMode;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 5:49 PM 2019/8/11
 **/
public class CacheManagerSolverSupport implements CacheManagerSolver {

    List<CacheManager> cacheManagers;

    @Override
    public List<CacheManager> getCacheManager(LinkedHashSet<CachingMode> modes) {
        List<Integer> cacheLines = getCacheLines(modes);
        return cacheManagers.stream().filter(cacheManager -> cacheLines
                .contains(cacheManager.getCacheLine()))
                .collect(Collectors.toList());
    }

    private List<Integer> getCacheLines(LinkedHashSet<CachingMode> modes) {
        return modes.stream().map(mode -> mode.getCacheLine()).collect(Collectors.toList());
    }


}
