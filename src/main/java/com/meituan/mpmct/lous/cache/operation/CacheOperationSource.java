package com.meituan.mpmct.lous.cache.operation;

import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 12:51 PM 2019/8/11
 **/
public interface CacheOperationSource {

    CacheOperation getCacheOperation(Class<?> target, Method method);
}
