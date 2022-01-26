package com.iyetoo.mpm.lous.keep.power.interceptor;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 8:34 PM 2019/8/19
 **/
public interface FilterablePowerHandler extends PowerHandler {

    Object filter();

    boolean proceed();
}
