package com.iyetoo.mpm.lous.cache.operation;

import com.iyetoo.mpm.lous.cache.annotation.CachingMode;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Method;
import java.util.LinkedHashSet;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 12:52 PM 2019/8/12
 **/
public class CacheOperationContext {

    private Method method;
    private Method targetMethod;

    private Class targetClass;
    private Object[] parameters;
    private Object targetObject;
    private String key;
    private Object generateKey;
    private String cacheName;
    private Object valueKey;
    private LinkedHashSet<CachingMode> cachingModes;


    public CacheOperationContext(Method method, Object targetObject, Object[] parameters, CacheOperation cacheOperation) {
        this.method = method;
        this.targetClass = targetObject.getClass();
        this.targetMethod = AopUtils.getMostSpecificMethod(method, targetObject.getClass());
        this.cacheName = cacheOperation.getCacheName();
        this.cachingModes = cacheOperation.getCachingModes();
        this.method = method;
        this.key = cacheOperation.getKey();
        this.targetObject = targetObject;
        this.parameters = parameters;
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


    public Method getTargetMethod() {
        return targetMethod;
    }

    public void setTargetMethod(Method targetMethod) {
        this.targetMethod = targetMethod;
    }

    public Object getValueKey() {
        return valueKey;
    }

    public void setValueKey(Object valueKey) {
        this.valueKey = valueKey;
    }
}
