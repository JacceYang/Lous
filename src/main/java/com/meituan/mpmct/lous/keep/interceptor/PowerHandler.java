package com.meituan.mpmct.lous.keep.interceptor;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 6:41 PM 2019/8/11
 **/
public interface PowerHandler {

    Integer PRE_HANDLE=1;

    Integer POST_HANDLE=2;
    /**
     * the power handler name
     *
     * @return
     */
    String getName();

}
