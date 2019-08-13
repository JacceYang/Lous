package com.meituan.mpmct.lous.cache.operation;

import com.meituan.mpmct.lous.cache.Cache;
import com.meituan.mpmct.lous.cache.CacheManager;
import com.meituan.mpmct.lous.cache.annotation.CachingMode;
import com.meituan.mpmct.lous.cache.support.CacheManagerSolverSupport;
import com.meituan.mpmct.lous.cache.support.CacheSolver;

import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 12:52 PM 2019/8/12
 **/
public class CacheOperationContext {

    List<CacheManager> cacheManager;
    List<Cache> caches;
    CacheManagerSolverSupport cacheManagerSolverSupport;
    CacheSolver cacheSolver;
    private Method method;
    private Class targetClass;
    private Object[] parameters;
    private Object targetObject;
    private String key;
    private Object generateKey;
    private String cacheName;
    private LinkedHashSet<CachingMode> cachingModes;


    public CacheOperationContext(Method method, Object targetObject, Object[] parameters, CacheOperation cacheOperation) {
        this.method = method;
        this.cacheName = cacheOperation.getCacheName();
        this.cachingModes = cacheOperation.getCachingModes();
        this.method = method;
        this.key = cacheOperation.getKey();
        this.targetObject = targetObject;
        this.parameters = parameters;
        List<CacheManager> cacheManager = cacheManagerSolverSupport.getCacheManager(cacheOperation.getCachingModes());
        List<Cache> caches = cacheSolver.determineUltimateCache(cacheManager, cacheOperation.getCacheName());
    }


    public List<CacheManager> getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(List<CacheManager> cacheManager) {
        this.cacheManager = cacheManager;
    }

    public List<Cache> getCaches() {
        return caches;
    }

    public void setCaches(List<Cache> caches) {
        this.caches = caches;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public Object getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getGenerateKey() {
        return generateKey;
    }

    public void setGenerateKey(Object generateKey) {
        this.generateKey = generateKey;
    }

    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public LinkedHashSet<CachingMode> getCachingModes() {
        return cachingModes;
    }

    public void setCachingModes(LinkedHashSet<CachingMode> cachingModes) {
        this.cachingModes = cachingModes;
    }
}
