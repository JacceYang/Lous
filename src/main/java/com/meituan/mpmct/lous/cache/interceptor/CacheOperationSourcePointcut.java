package com.meituan.mpmct.lous.cache.interceptor;

import com.meituan.mpmct.lous.cache.annotation.GetCache;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 10:11 PM 2019/8/9
 **/
public class CacheOperationSourcePointcut extends StaticMethodMatcherPointcut implements Serializable {

    @Override
    public boolean matches(Method method, Class<?> aClass) {
        Method specificMethod = AopUtils.getMostSpecificMethod(method, aClass);
        if (specificMethod.isAnnotationPresent(GetCache.class)) {
            return true;
        }
        return false;
    }
}
