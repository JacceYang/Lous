package com.meituan.mpmct.lous.keep.support;


import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 9:43 AM 2019/8/22
 **/
public abstract class AbstractPowerInvokeCollector implements PowerInvokeCollector{

    private Method method;
    private Class<?> targetClass;
    private Object targetObject;

    public AbstractPowerInvokeCollector(Method method, Class<?> targetClass,Object targetObject) {
        this.method = method;
        this.targetClass = targetClass;
        this.targetObject=targetObject;
    }

    protected abstract Object getExecutorObject();
}
