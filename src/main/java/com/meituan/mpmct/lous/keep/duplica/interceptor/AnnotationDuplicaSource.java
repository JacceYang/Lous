package com.meituan.mpmct.lous.keep.duplica.interceptor;

import com.meituan.mpmct.lous.keep.duplica.support.DuplicaSourceContextParser;
import org.springframework.beans.factory.BeanFactory;

import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 4:44 PM 2019/8/25
 **/
public class AnnotationDuplicaSource implements DuplicaSource {


    private static DuplicaSourceContextParser sourceContextParser = new DuplicaSourceContextParser();

    private static KeyExpressionEvaluator expressionEvaluator = new KeyExpressionEvaluator();

    @Override
    public DuplicaSourceContext getDuplicaSourceContext(Method method, Object targetObject, Object[] parameters, BeanFactory beanFactory) {
        DuplicaElement duplicaElement = sourceContextParser.computeDuplicaElement(method, targetObject.getClass());
        if (duplicaElement == null) {
            return null;
        }
        DuplicaSourceContext duplicaSourceContext = sourceContextParser.parseDuplicaSourceContext(method, targetObject.getClass(), parameters, duplicaElement, expressionEvaluator, beanFactory);
        return duplicaSourceContext;
    }
}
