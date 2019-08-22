package com.meituan.mpmct.lous.keep.support;

import org.springframework.beans.factory.BeanFactory;

import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 9:10 PM 2019/8/21
 **/
public class BeanPowerInvokeCollector extends AbstractPowerInvokeCollector {


    private BeanFactory  beanFactory;

    public BeanPowerInvokeCollector(Method method, Class<?> targetClass, Object targetObject, BeanFactory beanFactory) {
        super(method, targetClass, targetObject);
        this.beanFactory = beanFactory;
    }

    @Override
    public <T> T collect() {
        return null;
    }


    @Override
    protected Object getExecutorObject() {
        return null;
    }
}
