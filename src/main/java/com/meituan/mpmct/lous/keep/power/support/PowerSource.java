package com.meituan.mpmct.lous.keep.power.support;

import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 6:51 PM 2019/8/19
 **/
public interface PowerSource {

    PowerSourceContext getPowerSource(Method method, Class<?> targetClass, Object targetObject, Object[] parameters);
}
