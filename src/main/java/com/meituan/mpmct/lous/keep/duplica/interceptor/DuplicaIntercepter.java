package com.meituan.mpmct.lous.keep.duplica.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 10:36 PM 2019/8/18
 **/
public class DuplicaIntercepter extends DuplicaAspectSupport implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        DuplicaInvoker invoker=new DuplicaInvoker() {
            @Override
            public Object invoke() throws Throwable {
                return invocation.proceed();
            }
        };

        return execute(invoker,invocation.getMethod(),invocation.getStaticPart(),invocation.getArguments());
    }


}
