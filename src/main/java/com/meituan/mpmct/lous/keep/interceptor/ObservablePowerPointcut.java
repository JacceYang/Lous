package com.meituan.mpmct.lous.keep.interceptor;

import com.meituan.mpmct.lous.keep.annotation.Power;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Observable;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 12:52 PM 2019/8/23
 **/
public class ObservablePowerPointcut extends Observable implements Pointcut, MethodMatcher {

    PowerPointcut pointcut = new PowerPointcut();

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return pointcut.matches(method, targetClass);
    }

    @Override
    public boolean isRuntime() {
        return pointcut.isRuntime();
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        return pointcut.matches(method, targetClass, args);
    }

    @Override
    public ClassFilter getClassFilter() {
        return pointcut.getClassFilter();
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return pointcut.getMethodMatcher();
    }

    private class PowerPointcut extends StaticMethodMatcherPointcut implements Serializable {

        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            Method mostSpecificMethod = AopUtils.getMostSpecificMethod(method, targetClass);
            if (mostSpecificMethod.isAnnotationPresent(Power.class)) {
                Power annotation = mostSpecificMethod.getAnnotation(Power.class);
                ObservablePowerPointcut.this.setChanged();
                ObservablePowerPointcut.this.notifyObservers(annotation);
                return true;
            }
            return false;
        }
    }
}
