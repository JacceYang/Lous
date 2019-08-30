package com.iyetoo.mpm.lous.keep.power.support;

import java.lang.reflect.Method;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 6:51 PM 2019/8/19
 **/
public interface PowerSource {

    PowerSourceContext getPowerSource(Method method, Class<?> targetClass, Object targetObject, Object[] parameters);
}
