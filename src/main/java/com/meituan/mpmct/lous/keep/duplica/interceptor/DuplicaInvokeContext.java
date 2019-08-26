package com.meituan.mpmct.lous.keep.duplica.interceptor;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 9:02 PM 2019/8/25
 **/
public class DuplicaInvokeContext {

    /**
     * the request againts object。
     */
    private String anchor;

    /**
     * the request invoker id generate by rule .
     */
    private String key;

    /**
     * the request content used to just the same request.
     */
    private String content;
}
