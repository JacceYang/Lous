package com.iyetoo.mpm.lous.cache.operation;

import java.lang.reflect.Method;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 12:51 PM 2019/8/11
 **/
public interface CacheOperationSource {

    CacheOperation getCacheOperation(Class<?> target, Method method);
}
