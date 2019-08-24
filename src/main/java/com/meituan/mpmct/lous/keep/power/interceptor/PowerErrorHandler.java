package com.meituan.mpmct.lous.keep.power.interceptor;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 1:19 PM 2019/8/19
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
