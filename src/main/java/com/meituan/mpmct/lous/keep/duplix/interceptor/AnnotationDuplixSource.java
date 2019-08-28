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
    public DuplixSourceContext getDuplixSourceContext(Method method, Object targetObject, Object[] parameters, BeanFactory beanFactory) {

        DuplixElement duplixElement = sourceContextParser.computeDuplixElement(method, targetObject.getClass());
        if (duplixElement == null) {
            return null;
        }

        DuplixSourceParseContext parseContext=new DuplixSourceParseContext(method, targetObject.getClass(), parameters, duplixElement);
        DuplixSourceContext duplixSourceContext = sourceContextParser.parseDuplixSourceContext(parseContext, expressionEvaluator, beanFactory);
        return duplixSourceContext;
    }
}
