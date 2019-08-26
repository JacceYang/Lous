package com.meituan.mpmct.lous.keep.duplica.interceptor;

import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 9:26 AM 2019/8/25
 **/
public interface DuplicaSource {

    DuplicaSourceContext getDuplicaSourceContext(Method method,Object targetObject,Object[] parameters);
}
