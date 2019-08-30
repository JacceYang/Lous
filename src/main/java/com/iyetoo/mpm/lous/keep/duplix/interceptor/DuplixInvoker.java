package com.iyetoo.mpm.lous.keep.duplix.interceptor;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 10:41 PM 2019/8/24
 **/
@FunctionalInterface
public interface DuplixInvoker {

    Object invoke() throws Throwable;
}
