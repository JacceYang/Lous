package com.iyetoo.mpm.lous.keep.power.interceptor;

import com.iyetoo.mpm.lous.keep.power.support.PowerInvokeContext;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 1:14 PM 2019/8/20
 **/
public abstract class AbstractPostPowerHandler<T> implements PostPowerHandler {

    protected T invokeResult;

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
