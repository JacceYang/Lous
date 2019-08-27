package com.meituan.mpmct.lous.keep.power.interceptor;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 1:18 PM 2019/8/19
 **/
public interface ConfigurablePowerHandler extends FilterablePowerHandler, PowerHandlerContext {

    /**
     * config error handler for power handler
     *
     * @return
     */
    PowerErrorHandler getErrorHandler();

    /**
     * set error handler for {@link ConfigurablePowerHandler#proceed()}, when a error throw
     * use this handler will be called to  handle error。
     *
     * @param errorHandler
     */
    void setErrorHandler(PowerErrorHandler errorHandler);
}
