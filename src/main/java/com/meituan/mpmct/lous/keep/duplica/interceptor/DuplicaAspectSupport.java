package com.meituan.mpmct.lous.keep.duplica.interceptor;

import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 10:10 PM 2019/8/24
 **/
public class DuplicaAspectSupport {


    public Object execute(DuplicaInvoker invoker) throws Throwable {


        return invoker.invoke();
    }

}
