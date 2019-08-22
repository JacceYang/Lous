package com.meituan.mpmct.lous.keep.support;


import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 9:09 PM 2019/8/21
 **/
public class ThisPowerInvokeCollector extends AbstractPowerInvokeCollector {


    public ThisPowerInvokeCollector(Method method, Class<?> targetClass, Object targetObject) {
        super(method, targetClass, targetObject);
    }

    @Override
    protected Object getExecutorObject() {
        return null;
    }

    @Override
    public <T> T collect() {




        return null;
    }
}
