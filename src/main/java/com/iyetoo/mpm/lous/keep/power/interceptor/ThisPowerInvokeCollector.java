package com.iyetoo.mpm.lous.keep.power.interceptor;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 9:09 PM 2019/8/21
 **/
public class ThisPowerInvokeCollector extends AbstractPowerInvokeCollector {


    public ThisPowerInvokeCollector(Method method, Class<?> targetClass, Object targetObject, Object[] parameters) {
        super(method, targetClass, targetObject, parameters);
    }

    @Override
    public <T> T collect() throws InvocationTargetException, IllegalAccessException {
        return (T) method.invoke(targetObject, null);
    }
}
