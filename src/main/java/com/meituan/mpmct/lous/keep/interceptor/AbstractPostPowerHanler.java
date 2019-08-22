package com.meituan.mpmct.lous.keep.interceptor;

import com.meituan.mpmct.lous.keep.support.PowerInvokeContext;
import com.meituan.mpmct.lous.keep.support.PropertyInvokeContext;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 1:14 PM 2019/8/20
 **/
public abstract class AbstractPostPowerHanler<T> implements ConfigurablePowerHandler {

    private PowerErrorHandler  powerErrorHandler=new DefaultPowerErrorHandler();

    protected T invokeResult;


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
        this.powerErrorHandler=errorHandler;
    }

    @Override
    public PowerInvokeContext getContext() {
        return context;
    }

    @Override
    public void setContext(PowerInvokeContext context) {
        this.context=context;
    }

}
