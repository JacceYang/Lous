package com.iyetoo.mpm.lous.cache.interceptor;

import java.lang.reflect.Method;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 11:43 AM 2019/8/11
 **/
public class DefaultCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    public String generateKey(Object target, Method method, Object... args) {
        return null;
    }
}
