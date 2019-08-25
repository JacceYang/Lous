package com.meituan.mpmct.lous.keep.duplica.interceptor;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 10:10 PM 2019/8/24
 **/
public class DuplicaAspectSupport {


    public Object execute(DuplicaInvoker invoker, Method method, Object targetObject, Object[] prameter) throws Throwable {






        return invoker.invoke();
    }

}
