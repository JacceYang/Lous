package com.meituan.mpmct.lous.keep.interceptor;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 1:18 PM 2019/8/19
 **/
public interface ConfigurablePowerHandler extends FilterablePowerHandler{

    PowerErrorHandler getErrorHandler();

    void setErrorHandler(PowerErrorHandler errorHandler) ;
}
