package com.meituan.mpmct.lous.cache.support;

import com.meituan.mpmct.lous.cache.CacheManager;
import com.meituan.mpmct.lous.cache.annotation.CachingMode;

import java.util.List;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 5:49 PM 2019/8/11
 **/
public class CacheManagerSolverSupport implements CacheManagerSolver  {

    @Override
    public List<CacheManager> getCacheManager(List<CachingMode> modes) {
        return null;
    }


}
