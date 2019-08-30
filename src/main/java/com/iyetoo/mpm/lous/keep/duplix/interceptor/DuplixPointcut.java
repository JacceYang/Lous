package com.iyetoo.mpm.lous.keep.duplix.interceptor;

import com.iyetoo.mpm.lous.keep.annotation.Duplix;
import com.iyetoo.mpm.lous.keep.duplix.config.DuplixEvent;
import com.iyetoo.mpm.lous.keep.event.KeepEventCenter;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 10:47 PM 2019/8/24
 **/
public class DuplixPointcut extends StaticMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        Method mostSpecificMethod = AopUtils.getMostSpecificMethod(method, targetClass);
        if (mostSpecificMethod.isAnnotationPresent(Duplix.class)) {
            fireDuplixProxy(new DuplixEvent(mostSpecificMethod));
            return true;
        }
        return false;
    }

    private void fireDuplixProxy(DuplixEvent duplixEvent) {
        RequestMapping webRequest = AnnotatedElementUtils.getMergedAnnotation(duplixEvent.getMethod(), RequestMapping.class);
        if (webRequest != null) {
            KeepEventCenter.changed();
            KeepEventCenter.publishEvent(duplixEvent);
        }
    }
}
