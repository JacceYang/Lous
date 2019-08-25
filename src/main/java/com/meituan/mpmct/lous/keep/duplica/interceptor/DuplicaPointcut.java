package com.meituan.mpmct.lous.keep.duplica.interceptor;

import com.meituan.mpmct.lous.keep.annotation.Duplica;
import com.meituan.mpmct.lous.keep.duplica.config.DuplicaEvent;
import com.meituan.mpmct.lous.keep.event.ObservableEventCenter;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 10:47 PM 2019/8/24
 **/
public class DuplicaPointcut extends StaticMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        Method mostSpecificMethod = AopUtils.getMostSpecificMethod(method, targetClass);
        if (mostSpecificMethod.isAnnotationPresent(Duplica.class)){
            fireDuplicaProxy(new  DuplicaEvent(mostSpecificMethod));
            return true;
        }
        return false;
    }

    private void fireDuplicaProxy(DuplicaEvent duplicaEvent){
        if (duplicaEvent.getMethod().isAnnotationPresent(GetMapping.class)){
            ObservableEventCenter.changed();
            ObservableEventCenter.publishEvent(duplicaEvent);
        }
    }
}
