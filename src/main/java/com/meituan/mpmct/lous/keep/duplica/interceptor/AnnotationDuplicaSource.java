package com.meituan.mpmct.lous.keep.duplica.interceptor;

import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 4:44 PM 2019/8/25
 **/
public class AnnotationDuplicaSource implements DuplicaSource {


    @Override
    public DuplicaSourceContext getDuplicaSourceContext(Method method, Object targetObject, Object[] parameters) {


        return null;
    }
}