package com.iyetoo.mpm.lous.keep.power.interceptor;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 6:41 PM 2019/8/11
 **/
public interface PowerHandler {

    Integer PRE_HANDLE = 1;

    Integer POST_HANDLE = 2;

    /**
     * the power handler name
     *
     * @return
     */
    String getName();

}
