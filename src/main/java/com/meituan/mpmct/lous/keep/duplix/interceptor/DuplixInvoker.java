package com.meituan.mpmct.lous.keep.duplix.interceptor;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 10:41 PM 2019/8/24
 **/
@FunctionalInterface
public interface DuplixInvoker {

    Object invoke() throws Throwable;
}
