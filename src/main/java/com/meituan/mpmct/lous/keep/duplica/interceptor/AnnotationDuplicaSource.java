package com.meituan.mpmct.lous.keep.duplica.interceptor;

import com.meituan.mpmct.lous.keep.duplica.support.DuplicaSourceContextParser;

import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 4:44 PM 2019/8/25
 **/
public class AnnotationDuplicaSource implements DuplicaSource {


    DuplicaSourceContextParser sourceContextParser=new DuplicaSourceContextParser();

    @Override
    public DuplicaSourceContext getDuplicaSourceContext(Method method, Object targetObject, Object[] parameters) {
        DuplicaElement duplicaElement = sourceContextParser.computeDuplicaElement(method, targetObject.getClass());
        if (duplicaElement==null){
            return null;
        }

        DuplicaSourceContext duplicaSourceContext = sourceContextParser.parseDuplicaSourceContext(method,targetObject.getClass(),duplicaElement);
        return duplicaSourceContext;
    }
}
