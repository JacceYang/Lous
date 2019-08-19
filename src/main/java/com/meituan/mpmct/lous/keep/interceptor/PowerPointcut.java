package com.meituan.mpmct.lous.keep.interceptor;

import com.meituan.mpmct.lous.keep.annotation.Power;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.io.Serializable;
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
            return true;
        }
        return false;
    }
}
