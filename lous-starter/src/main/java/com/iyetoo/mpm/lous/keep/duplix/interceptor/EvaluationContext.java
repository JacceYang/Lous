package com.iyetoo.mpm.lous.keep.duplix.interceptor;

import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 4:39 PM 2019/8/27
 **/
public class EvaluationContext extends MethodBasedEvaluationContext {

    public EvaluationContext(Object rootObject, Method method, Object[] arguments, ParameterNameDiscoverer parameterNameDiscoverer) {
        super(rootObject, method, arguments, parameterNameDiscoverer);
    }
}
