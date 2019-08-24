package com.meituan.mpmct.lous.keep.duplica.interceptor;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 10:41 PM 2019/8/24
 **/
@FunctionalInterface
public interface DuplicaInvoker {

    Object invoke() throws Throwable;
}
