package com.meituan.mpmct.lous.keep.interceptor;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 6:44 PM 2019/8/11
 **/
public abstract class PostPowerHandler implements ConfigurablePowerHandler {

    private PowerErrorHandler  powerErrorHandler=new DefaultPowerErrorHandler();

    @Override
    public PowerErrorHandler getErrorHandler() {
        return powerErrorHandler;
    }

    @Override
    public void setErrorHandler(PowerErrorHandler errorHandler) {
        this.powerErrorHandler=errorHandler;
    }
}
