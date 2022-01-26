package com.iyetoo.mpm.lous.keep.power.support;

import com.iyetoo.mpm.lous.keep.power.interceptor.*;

import java.util.List;
import java.util.Set;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 5:13 PM 2019/8/19
 **/
public interface GlobalPowerHandler {

    List<PrePowerHandler> getPrePowerHandler(Set<String> handlers);

    List<PostPowerHandler> getPostPowerHandler(Set<String> handlers);

    PowerErrorHandler getErrorHandler(String errorHandler);

    List<PowerChainHandler> getPowerChainHandler(Set<String> handlers);

    PowerInvokeCollector getPowerInvokeCollector(PowerInvokeCollectorContext collector);
}
