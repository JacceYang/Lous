package com.iyetoo.mpm.lous.keep.power.interceptor;

import com.iyetoo.mpm.lous.keep.power.support.PowerInvokeContext;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 1:11 PM 2019/8/20
 **/
public interface PowerHandlerContext {

    PowerInvokeContext getContext();

    void setContext(PowerInvokeContext context);
}
