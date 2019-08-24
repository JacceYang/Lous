package com.meituan.mpmct.lous.keep.power.interceptor;

import com.meituan.mpmct.lous.keep.power.support.PowerInvokeContext;

/**
 * This class
 *
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 6:43 PM 2019/8/11
 **/
public abstract class AbstractPrePowerHandler<T> implements PrePowerHandler {

    /**
     *  @criterion is recommended being assigned a value in {@link AbstractPrePowerHandler#filter} function,
     *  so you can use it in {@link AbstractPrePowerHandler#proceed()}
     */
    protected T criterion;

    private PowerErrorHandler powerErrorHandler = new DefaultPowerErrorHandler();
    /**
     * method invoke context. it contains method context and dependency context data.
     */
    private PowerInvokeContext context;

    @Override
    public PowerErrorHandler getErrorHandler() {
        return powerErrorHandler;
    }

    @Override
    public void setErrorHandler(PowerErrorHandler errorHandler) {
        this.powerErrorHandler = errorHandler;
    }

    @Override
    public PowerInvokeContext getContext() {
        return context;
    }

    @Override
    public void setContext(PowerInvokeContext context) {
        this.context = context;
    }

}
