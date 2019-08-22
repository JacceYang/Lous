package com.meituan.mpmct.lous.keep.support;

import org.springframework.core.MethodClassKey;

import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 10:54 AM 2019/8/22
 **/
public class PowerInvokeCollectorContext {

    private String collector;

    private Method method;

    private Class<?>  targetClass;

    private Object targetObject;

    private Object[] parameters;

    public PowerInvokeCollectorContext(String collector, Method method,Class targetClass, Object targetObject,Object[] parameters) {
        this.collector = collector;
        this.method = method;
        this.targetClass=targetClass;
        this.targetObject = targetObject;
        this.parameters=parameters;
    }

    public String getCollector() {
        return collector;
    }

    public Method getMethod() {
        return method;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Object getTargetObject() {
        return targetObject;
    }

    public Object[] getParameters() {
        return parameters;
    }
}
