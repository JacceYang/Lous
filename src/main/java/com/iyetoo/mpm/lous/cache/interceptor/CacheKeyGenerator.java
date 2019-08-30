package com.iyetoo.mpm.lous.cache.interceptor;

import java.lang.reflect.Method;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 11:40 AM 2019/8/11
 **/
public interface CacheKeyGenerator {

    /**
     * @param target
     * @param method
     * @param args
     * @return
     */
    String generateKey(Object target, Method method, Object... args);

}
