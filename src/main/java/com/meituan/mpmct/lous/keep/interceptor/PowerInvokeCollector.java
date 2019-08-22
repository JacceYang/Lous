package com.meituan.mpmct.lous.keep.interceptor;

import java.lang.reflect.InvocationTargetException;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 9:05 PM 2019/8/20
 **/
public interface PowerInvokeCollector {

    /**
     * @param <T>
     * @return return the collect value from outer bean or target object.
     */
    <T> T collect() throws InvocationTargetException,IllegalAccessException;
}
