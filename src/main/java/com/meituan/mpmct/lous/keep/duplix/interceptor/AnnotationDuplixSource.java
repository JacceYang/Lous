package com.meituan.mpmct.lous.keep.duplix.interceptor;

import com.meituan.mpmct.lous.keep.duplix.support.DuplixSourceContextParser;
import org.springframework.beans.factory.BeanFactory;

import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 4:44 PM 2019/8/25
 **/
public class AnnotationDuplixSource implements DuplixSource {


    private static DuplixSourceContextParser sourceContextParser = new DuplixSourceContextParser();

    private static KeyExpressionEvaluator expressionEvaluator = new KeyExpressionEvaluator();

    @Override
    public DuplixSourceContext getDuplicaSourceContext(Method method, Object targetObject, Object[] parameters, BeanFactory beanFactory) {
        DuplixElement duplixElement = sourceContextParser.computeDuplicaElement(method, targetObject.getClass());
        if (duplixElement == null) {
            return null;
        }
        DuplixSourceContext duplixSourceContext = sourceContextParser.parseDuplicaSourceContext(method, targetObject.getClass(), parameters, duplixElement, expressionEvaluator, beanFactory);
        return duplixSourceContext;
    }
}
