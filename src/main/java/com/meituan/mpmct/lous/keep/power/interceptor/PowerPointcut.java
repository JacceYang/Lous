package com.meituan.mpmct.lous.keep.power.interceptor;

import com.meituan.mpmct.lous.keep.annotation.Power;
import com.meituan.mpmct.lous.keep.event.KeepEventCenter;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description: 网关拦截器, 切点逻辑
 * @Data:Initialized in 12:52 PM 2019/8/19
 **/
public class PowerPointcut extends StaticMethodMatcherPointcut implements Serializable {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        Method mostSpecificMethod = AopUtils.getMostSpecificMethod(method, targetClass);
        if (mostSpecificMethod.isAnnotationPresent(Power.class)) {
            Power annotation = method.getAnnotation(Power.class);
            firePowerProxy(annotation);
            return true;
        }
        return false;
    }

    private void firePowerProxy(Annotation annotation) {
        KeepEventCenter.changed();
        KeepEventCenter.publishEvent(annotation);
    }
}
