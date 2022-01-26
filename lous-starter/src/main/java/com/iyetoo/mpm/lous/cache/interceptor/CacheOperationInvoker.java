package com.iyetoo.mpm.lous.cache.interceptor;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 3:07 PM 2019/8/11
 **/
@FunctionalInterface
public interface CacheOperationInvoker {

    Object invoke();
}
