package com.meituan.mpmct.lous.keep.interceptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 9:10 PM 2019/8/21
 **/
public class BeanPowerInvokeCollector extends AbstractPowerInvokeCollector {

    public BeanPowerInvokeCollector(Method method, Class<?> targetClass, Object targetObject, Object[] parameters) {
        super(method, targetClass, targetObject, parameters);
    }

    @Override
    public <T> T collect() throws InvocationTargetException, IllegalAccessException {
        Object result = method.invoke(targetObject, null);
        return result == null ? null : (T) result;
    }
}
