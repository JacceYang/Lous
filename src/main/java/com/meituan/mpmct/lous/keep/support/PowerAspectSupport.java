package com.meituan.mpmct.lous.keep.support;

import com.meituan.mpmct.lous.keep.interceptor.PowerInvoker;

import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 1:26 PM 2019/8/19
 **/
public class PowerAspectSupport {

    protected Object execute(PowerInvoker invoker, Method method,Object[] parameters,Object targetObject){






        return invoker.invoke();
    }
}
