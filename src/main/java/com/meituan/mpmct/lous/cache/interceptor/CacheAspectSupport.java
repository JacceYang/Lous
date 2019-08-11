package com.meituan.mpmct.lous.cache.interceptor;

import com.meituan.mpmct.lous.cache.operation.CacheOperation;
import com.meituan.mpmct.lous.cache.operation.CacheOperationSource;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 3:11 PM 2019/8/11
 **/
public class CacheAspectSupport {


    CacheOperationSource cacheOperationSource;


    public CacheOperationSource getCacheOperationSource() {
        return cacheOperationSource;
    }

    public void setCacheOperationSource(CacheOperationSource cacheOperationSource) {
        this.cacheOperationSource = cacheOperationSource;
    }

    protected Object execute(CacheOperationInvoker invoker, Object target, Method method, Object... args){
        Class<?> targetClass = getTargetClass(target);
        CacheOperationSource cacheOperationSource = getCacheOperationSource();
        CacheOperation cacheOperation = cacheOperationSource.getCacheOperation(targetClass, method);




        return invoker.invoke();
    }


    private Class<?> getTargetClass(Object target){
        return AopProxyUtils.ultimateTargetClass(target);
    }
}
