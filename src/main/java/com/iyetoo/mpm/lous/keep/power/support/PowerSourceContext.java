package com.iyetoo.mpm.lous.keep.power.support;

import com.iyetoo.mpm.lous.keep.power.interceptor.*;

import java.util.List;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 2:45 PM 2019/8/19
 **/
public class PowerSourceContext {

    private List<PrePowerHandler> preHandlers;

    private List<PostPowerHandler> postPowerHandlers;

    private List<PowerChainHandler> powerChainHandlers;

    private PowerErrorHandler errorHandler;

    private PowerInvokeCollector invokeCollector;


    public List<PrePowerHandler> getPreHandlers() {
        return preHandlers;
    }

    public void setPreHandlers(List<PrePowerHandler> preHandlers) {
        this.preHandlers = preHandlers;
    }

    public List<PostPowerHandler> getPostPowerHandlers() {
        return postPowerHandlers;
    }

    public void setPostPowerHandlers(List<PostPowerHandler> postPowerHandlers) {
        this.postPowerHandlers = postPowerHandlers;
    }

    public List<PowerChainHandler> getPowerChainHandlers() {
        return powerChainHandlers;
    }

    public void setPowerChainHandlers(List<PowerChainHandler> powerChainHandlers) {
        this.powerChainHandlers = powerChainHandlers;
    }

    public PowerErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public void setErrorHandler(PowerErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public PowerInvokeCollector getInvokeCollector() {
        return invokeCollector;
    }

    public void setInvokeCollector(PowerInvokeCollector invokeCollector) {
        this.invokeCollector = invokeCollector;
    }
}
