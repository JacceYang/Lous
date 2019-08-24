package com.meituan.mpmct.lous.keep.power.interceptor;

import com.meituan.mpmct.lous.keep.power.support.PowerInvokeContext;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 1:11 PM 2019/8/20
 **/
public interface PowerHandlerContext {

    PowerInvokeContext getContext();

    void setContext(PowerInvokeContext context);
}
