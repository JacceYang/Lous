package com.iyetoo.mpm.lous.keep.duplix.config;

import java.lang.reflect.Method;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 2:33 PM 2019/8/25
 **/
public class DuplixEvent {

    private Method method;

    public DuplixEvent(Method method) {
        this.method = method;
    }

    public Method getMethod() {
        return method;
    }
}
