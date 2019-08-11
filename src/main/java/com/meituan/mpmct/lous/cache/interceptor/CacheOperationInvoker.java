package com.meituan.mpmct.lous.cache.interceptor;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 3:07 PM 2019/8/11
 **/
@FunctionalInterface
public interface CacheOperationInvoker {

    Object invoke();
}
