package com.iyetoo.mpm.lous.keep.power.interceptor;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 1:19 PM 2019/8/19
 **/
public interface PowerErrorHandler extends PowerHandler {

    /**
     * @param throwable
     */
    void error(Throwable throwable);

    /**
     * @param msg
     */
    void message(String msg);
}
