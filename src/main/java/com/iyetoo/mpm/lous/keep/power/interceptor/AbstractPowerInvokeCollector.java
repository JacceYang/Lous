package com.iyetoo.mpm.lous.keep.power.interceptor;


import java.lang.reflect.Method;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 9:43 AM 2019/8/22
 **/
public abstract class AbstractPowerInvokeCollector implements PowerInvokeCollector {

    protected Method method;
    protected Class<?> targetClass;
    protected Object targetObject;
    Object[] parameters;

    public AbstractPowerInvokeCollector(Method method, Class<?> targetClass, Object targetObject, Object[] parameters) {
        this.method = method;
        this.targetClass = targetClass;
        this.targetObject = targetObject;
        this.parameters = parameters;
    }
}
