package com.meituan.mpmct.lous.keep.duplix.support;

import com.meituan.mpmct.lous.keep.annotation.Duplix;
import com.meituan.mpmct.lous.keep.annotation.Scene;
import com.meituan.mpmct.lous.keep.duplix.interceptor.*;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.MethodClassKey;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 10:15 AM 2019/8/26
 **/
public class DuplixSourceContextParser {

    public DuplixElement computeDuplixElement(Method method, Class<?> targetClass) {
        Method mostSpecificMethod = AopUtils.getMostSpecificMethod(method, targetClass);
        if (mostSpecificMethod != null && mostSpecificMethod.isAnnotationPresent(Duplix.class)) {
            return parseDuplixElement(mostSpecificMethod);
        }
        return null;
    }

    private DuplixElement parseDuplixElement(Method method) {
        Set<Duplix> allMergedAnnotations = AnnotatedElementUtils.findAllMergedAnnotations(method, Duplix.class);

        if (allMergedAnnotations != null) {
            Assert.isTrue(allMergedAnnotations.size() == 1, "more than one Duplix annotation define on method is prohibit!!!");
            DuplixElement.Builder builder = new DuplixElement.Builder();
            Duplix next = allMergedAnnotations.iterator().next();

            builder.setExpire(next.expire());
            builder.setKey(next.key());
            builder.setTimes(next.times());
            builder.setScene(next.scene());
            builder.setUnit(next.unit());
            builder.setMsg(next.msg());
            return builder.build();
        }
        return null;
    }

    public DuplixSourceContext parseDuplixSourceContext(DuplixSourceParseContext parseContext, KeyExpressionEvaluator expressionEvaluator, BeanFactory beanFactory) {

        Assert.notNull(parseContext.getElement(), "duplicate element shouldn't be null");
        DuplixSourceContext sourceContext = new DuplixSourceContext();
        EvaluationContext evaluationContext = expressionEvaluator.createEvaluationContext(parseContext.getMethod(), parseContext.getParameters(), parseContext.getTargetClass(), beanFactory);
        String storeKey = expressionEvaluator.key(parseContext.getElement().getKey(), new MethodClassKey(parseContext.getMethod(), parseContext.getTargetClass()), evaluationContext).toString();
        if (parseContext.getElement().getScene() == Scene.WEB) {
            sourceContext.setRequestURI(new WebRequestURI(parseContext.getMethod(), parseContext.getTargetClass(), parseContext.getElement().getKey()));
        } else {
            sourceContext.setRequestURI(new MethodRequestURI(new MethodClassKey(parseContext.getMethod(), parseContext.getTargetClass()), storeKey));
        }
        long ms = parseContext.getElement().getUnit().toMillis(parseContext.getElement().getExpire());
        sourceContext.setExpire(ms);
        sourceContext.setKey(storeKey);
        sourceContext.setTimes(parseContext.getElement().getTimes());
        sourceContext.setMsg(parseContext.getElement().getMsg());
        sourceContext.setParameters(new DuplixSourceContext.RequestParameter(parseContext.getParameters()));
        return sourceContext;
    }
}
