package com.meituan.mpmct.lous.keep.support;


import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 9:43 AM 2019/8/22
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

    protected abstract Object getExecutorObject();
}
