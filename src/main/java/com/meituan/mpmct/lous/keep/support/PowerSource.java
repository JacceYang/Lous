package com.meituan.mpmct.lous.keep.support;

import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 6:51 PM 2019/8/19
 *
 **/
public interface PowerSource {

     PowerSourceContext getPowerSource(Method method,Class<?> targetClass);
}
