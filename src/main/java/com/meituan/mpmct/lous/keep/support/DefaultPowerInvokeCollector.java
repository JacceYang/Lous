package com.meituan.mpmct.lous.keep.support;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 9:28 PM 2019/8/20
 **/
public class DefaultPowerInvokeCollector implements PowerInvokeCollector {

    private Method method;

    private Object targetObject;

    private Object[] parameters;

    public DefaultPowerInvokeCollector(Method method, Object targetObject, Object[] parameters) {
        this.method = method;
        this.targetObject = targetObject;
        this.parameters = parameters;
    }

    @Override
    public <T> T collect() {
        try {
            return (T) method.invoke(targetObject, parameters);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
