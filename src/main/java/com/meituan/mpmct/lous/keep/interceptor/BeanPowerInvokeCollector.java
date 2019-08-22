package com.meituan.mpmct.lous.keep.interceptor;

import com.meituan.mpmct.lous.keep.interceptor.AbstractPowerInvokeCollector;

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
    public <T> T collect() {
        Object result = null;
        try {
            result = method.invoke(targetObject, null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result == null ? null : (T) result;
    }
}
