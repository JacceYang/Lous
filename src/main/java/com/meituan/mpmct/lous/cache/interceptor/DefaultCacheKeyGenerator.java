package com.meituan.mpmct.lous.cache.interceptor;

import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 11:43 AM 2019/8/11
 **/
public class DefaultCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    public String generateKey(Object target, Method method, Object... args) {
        return null;
    }
}
