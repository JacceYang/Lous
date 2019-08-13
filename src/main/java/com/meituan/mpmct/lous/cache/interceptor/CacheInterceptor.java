package com.meituan.mpmct.lous.cache.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.io.Serializable;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 10:53 PM 2019/8/9
 **/
public class CacheInterceptor extends CacheAspectSupport implements MethodInterceptor, Serializable {


    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        CacheOperationInvoker invoker = () -> {
            try {
                return methodInvocation.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return null;
        };

        return execute(invoker, methodInvocation.getThis(), methodInvocation.getMethod(), methodInvocation.getArguments());
    }
}
