package com.meituan.mpmct.lous.keep.duplica.interceptor;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodClassKey;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 1:55 PM 2019/8/27
 **/
public class KeyExpressionEvaluator {


    private static final Map<MethodClassKey, Expression> expressionCache = new HashMap<>();
    private static final ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
    private final SpelExpressionParser parser;

    public KeyExpressionEvaluator(SpelExpressionParser parser) {
        Assert.notNull(parser, "SpelExpressionParser must not be null");
        this.parser = parser;
    }

    public KeyExpressionEvaluator() {
        this(new SpelExpressionParser());
    }

    public SpelExpressionParser getParser() {
        return parser;
    }

    public ParameterNameDiscoverer getParameterNameDiscoverer() {
        return parameterNameDiscoverer;
    }

    public Expression getExpression(MethodClassKey methodClassKey, String expression) {
        Expression expr = expressionCache.get(methodClassKey);
        if (expr == null) {
            expr = getParser().parseExpression(expression);
            expressionCache.put(methodClassKey, expr);
        }
        return expr;
    }

    public EvaluationContext createEvaluationContext(Method method, Object[] args, Class<?> clazz, @Nullable BeanFactory beanFactory) {
        EvaluationContext evaluationContext = new EvaluationContext(new Object(), method, args, getParameterNameDiscoverer());

        if (beanFactory != null) {
            evaluationContext.setBeanResolver(new BeanFactoryResolver(beanFactory));
        }
        return evaluationContext;
    }

    public Object key(String expression, MethodClassKey methodClassKey, EvaluationContext evaluationContext) {
        return getExpression(methodClassKey, expression).getValue(evaluationContext);
    }


}
