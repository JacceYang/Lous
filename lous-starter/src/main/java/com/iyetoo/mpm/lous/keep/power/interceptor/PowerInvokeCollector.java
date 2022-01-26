package com.iyetoo.mpm.lous.keep.power.interceptor;

import java.lang.reflect.InvocationTargetException;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 9:05 PM 2019/8/20
 **/
public interface PowerInvokeCollector {

    /**
     * @param <T>
     * @return return the collect value from outer bean or target object.
     */
    <T> T collect() throws InvocationTargetException, IllegalAccessException;
}
