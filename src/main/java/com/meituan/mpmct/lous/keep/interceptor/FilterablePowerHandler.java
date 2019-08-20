package com.meituan.mpmct.lous.keep.interceptor;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 8:34 PM 2019/8/19
 **/
public interface FilterablePowerHandler extends PowerHandler{

     Object filter();

     boolean proceed();
}
