package com.meituan.mpmct.lous.keep.interceptor;

import com.meituan.mpmct.lous.keep.support.PowerAspectSupport;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 8:08 AM 2019/8/19
 **/
public class PowerInterceptor extends PowerAspectSupport implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // 回调方法
        PowerInvoker invoker = () -> {
            try {
                return invocation.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return null;
        };

        return execute(invoker, invocation.getMethod(), invocation.getArguments(), invocation.getThis());
    }
}
