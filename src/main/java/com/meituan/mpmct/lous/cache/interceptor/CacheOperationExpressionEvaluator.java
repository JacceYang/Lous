package com.meituan.mpmct.lous.cache.interceptor;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodClassKey;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 1:03 PM 2019/8/14
 **/
public class CacheOperationExpressionEvaluator {

    private Map<MethodClassKey, Expression> operationExp = new ConcurrentHashMap<>(16);

    private ExpressionParser parser = new SpelExpressionParser();

    private ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    public CacheOperationExpressionEvaluator() {
    }


    public Expression getExpression(Method method, Class<?> clazz, BeanFactory beanFactory, String key) {
        MethodClassKey methodClassKey = new MethodClassKey(method, clazz);
        Expression expression = operationExp.get(methodClassKey);
        if (expression == null) {
            expression = parser.parseExpression(key);
            //TO do  there maybe performance issue
            operationExp.put(methodClassKey, expression);
        }
        return operationExp.get(methodClassKey);
    }


}
