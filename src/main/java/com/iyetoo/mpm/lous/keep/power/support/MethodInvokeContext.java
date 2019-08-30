package com.iyetoo.mpm.lous.keep.power.support;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 1:53 PM 2019/8/20
 **/
public interface MethodInvokeContext {

    /**
     * Get the method invoke parameters ,the order is consistent with the current method
     *
     * @return
     */
    Object[] getMethodParameters();

    /**
     * @return
     */
    int getMethodParameterCount();

    /**
     * @param index
     * @return
     */
    Object getMethodParameter(int index);

    /**
     * @param index
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T getMethodParameter(int index, Class<T> clazz);

}
