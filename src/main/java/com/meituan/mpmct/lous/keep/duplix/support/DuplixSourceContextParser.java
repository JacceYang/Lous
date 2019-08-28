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

    public DuplixElement computeDuplicaElement(Method method, Class<?> targetClass) {
        Method mostSpecificMethod = AopUtils.getMostSpecificMethod(method, targetClass);
        if (mostSpecificMethod != null && mostSpecificMethod.isAnnotationPresent(Duplix.class)) {
            return parseDuplicaElement(mostSpecificMethod);
        }
        return null;
    }

    private DuplixElement parseDuplicaElement(Method method) {
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

    public DuplixSourceContext parseDuplicaSourceContext(Method method, Class<?> targetClass, Object[] parameters, DuplixElement element, KeyExpressionEvaluator expressionEvaluator, BeanFactory beanFactory) {

        Assert.notNull(element, "duplicate element shouldn't be null");
        DuplixSourceContext sourceContext = new DuplixSourceContext();
        EvaluationContext evaluationContext = expressionEvaluator.createEvaluationContext(method, parameters, targetClass, beanFactory);
        String storeKey = expressionEvaluator.key(element.getKey(), new MethodClassKey(method, targetClass), evaluationContext).toString();
        if (element.getScene() == Scene.WEB) {
            sourceContext.setRequestURI(new WebRequestURI(method, targetClass, element.getKey()));
        } else {
            sourceContext.setRequestURI(new MethodRequestURI(new MethodClassKey(method, targetClass), storeKey));
        }
        long ms = element.getUnit().toMillis(element.getExpire());
        sourceContext.setExpire(ms);
        sourceContext.setKey(storeKey);
        sourceContext.setTimes(element.getTimes());
        sourceContext.setMsg(element.getMsg());
        sourceContext.setParameters(new DuplixSourceContext.RequestParameter(parameters));
        return sourceContext;
    }
}
