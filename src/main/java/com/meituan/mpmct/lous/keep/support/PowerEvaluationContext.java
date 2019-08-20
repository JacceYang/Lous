package com.meituan.mpmct.lous.keep.support;

import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 7:39 PM 2019/8/20
 **/
public class PowerEvaluationContext extends MethodBasedEvaluationContext{

    public PowerEvaluationContext(Object rootObject, Method method, Object[] arguments, ParameterNameDiscoverer parameterNameDiscoverer) {
        super(rootObject, method, arguments, parameterNameDiscoverer);
    }

}
