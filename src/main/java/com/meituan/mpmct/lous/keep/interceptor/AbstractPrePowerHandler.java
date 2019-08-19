package com.meituan.mpmct.lous.keep.interceptor;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 6:43 PM 2019/8/11
 **/
public abstract class AbstractPrePowerHandler<T> implements ConfigurablePowerHandler {

    private PowerErrorHandler  powerErrorHandler=new DefaultPowerErrorHandler();

    protected T invokeResult;

    @Override
    public PowerErrorHandler getErrorHandler() {
        return powerErrorHandler;
    }

    @Override
    public void setErrorHandler(PowerErrorHandler errorHandler) {
        this.powerErrorHandler=errorHandler;
    }

}
