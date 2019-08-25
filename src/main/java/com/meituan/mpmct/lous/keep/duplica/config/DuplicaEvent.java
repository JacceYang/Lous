package com.meituan.mpmct.lous.keep.duplica.config;

import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 2:33 PM 2019/8/25
 **/
public class DuplicaEvent {

    private Method method;

    public DuplicaEvent(Method method) {
        this.method = method;
    }

    public Method getMethod() {
        return method;
    }
}
