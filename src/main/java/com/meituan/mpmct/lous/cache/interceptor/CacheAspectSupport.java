package com.meituan.mpmct.lous.cache.interceptor;

import com.meituan.mpmct.lous.cache.Cache;
import com.meituan.mpmct.lous.cache.CacheManager;
import com.meituan.mpmct.lous.cache.operation.CacheOperation;
import com.meituan.mpmct.lous.cache.operation.CacheOperationContext;
import com.meituan.mpmct.lous.cache.operation.CacheOperationSource;
import com.meituan.mpmct.lous.cache.support.CacheManagerSolver;
import com.meituan.mpmct.lous.cache.support.CacheManagerSolverSupport;
import com.meituan.mpmct.lous.cache.support.CacheSolver;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.SmartInitializingSingleton;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 3:11 PM 2019/8/11
 **/
public class CacheAspectSupport{

    private CacheManagerSolver cacheManagerSolver;
    private CacheSolver cacheSolver;
    private CacheOperationSource cacheOperationSource;


    public CacheOperationSource getCacheOperationSource() {
        return cacheOperationSource;
    }

    public void setCacheOperationSource(CacheOperationSource cacheOperationSource) {
        this.cacheOperationSource = cacheOperationSource;
    }

    protected Object execute(CacheOperationInvoker invoker, Object target, Method method, Object... args) {
        Class<?> targetClass = getTargetClass(target);
        CacheOperationSource cacheOperationSource = getCacheOperationSource();
        CacheOperation cacheOperation = cacheOperationSource.getCacheOperation(targetClass, method);


        Object execute = execute(new CacheOperationContext(method, target, args, cacheOperation));
        if (execute != null) {
            return execute;
        }

        return invoker.invoke();
    }

    private Object execute(CacheOperationContext operationContext) {
        List<CacheManager> cacheManager = getCacheManagerSolver().getCacheManager(operationContext.getCachingModes());

        for (Cache cache : getCacheSolver().determineUltimateCache(cacheManager,operationContext.getCacheName())) {
            Object value = cache.getValue(operationContext.getGenerateKey());
            if (value != null) {
                return value;
            }
        }
        return null;
    }


    private Class<?> getTargetClass(Object target) {
        return AopProxyUtils.ultimateTargetClass(target);
    }

    public CacheManagerSolver getCacheManagerSolver() {
        return cacheManagerSolver;
    }

    public void setCacheManagerSolver(CacheManagerSolver cacheManagerSolver) {
        this.cacheManagerSolver = cacheManagerSolver;
    }

    public CacheSolver getCacheSolver() {
        return cacheSolver;
    }

    public void setCacheSolver(CacheSolver cacheSolver) {
        this.cacheSolver = cacheSolver;
    }

}
