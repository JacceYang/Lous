package com.iyetoo.mpm.lous.keep.duplix.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 10:36 PM 2019/8/18
 **/
public class DuplixIntercepter extends DuplixAspectSupport implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        DuplixInvoker invoker = new DuplixInvoker() {
            @Override
            public Object invoke() throws Throwable {
                return invocation.proceed();
            }
        };

        return execute(invoker, invocation.getMethod(), invocation.getThis(), invocation.getArguments());
    }

}
