package com.meituan.mpmct.lous.keep.duplix.interceptor;

import com.meituan.mpmct.lous.keep.annotation.Duplix;
import com.meituan.mpmct.lous.keep.duplix.config.DuplixEvent;
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
public class DuplixPointcut extends StaticMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        Method mostSpecificMethod = AopUtils.getMostSpecificMethod(method, targetClass);
        if (mostSpecificMethod.isAnnotationPresent(Duplix.class)) {
            fireDuplicaProxy(new DuplixEvent(mostSpecificMethod));
            return true;
        }
        return false;
    }

    private void fireDuplicaProxy(DuplixEvent duplixEvent) {
        if (duplixEvent.getMethod().isAnnotationPresent(GetMapping.class)) {
            ObservableEventCenter.changed();
            ObservableEventCenter.publishEvent(duplixEvent);
        }
    }
}
