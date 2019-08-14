package com.meituan.mpmct.lous.cache.interceptor;

import com.meituan.mpmct.lous.cache.operation.CacheOperationContext;
import com.meituan.mpmct.lous.cache.operation.CacheOperationSource;
import com.sun.org.apache.xalan.internal.extensions.ExpressionContext;
import org.springframework.core.MethodClassKey;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 1:03 PM 2019/8/14
 **/
public class CacheOperationExpressionEvaluator {

    private Map<MethodClassKey,Object> operationExp=new ConcurrentHashMap<>(16);

    private ExpressionParser parser=new SpelExpressionParser();


    public CacheOperationExpressionEvaluator(MethodClassKey methodClassKey, CacheOperationContext context) {

        Expression expression = parser.parseExpression(context.getKey());
        ExpressionContext expressionContext=new ExpressionContext();
        expression.getValue();
    }
}
