package com.meituan.mpmct.lous.keep.support;

import com.meituan.mpmct.lous.keep.interceptor.*;

import java.util.List;
import java.util.Set;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 5:13 PM 2019/8/19
 **/
public interface GlobalPowerHandler {

    List<AbstractPrePowerHandler> getPrePowerHandler(Set<String> handlers);

    List<PostPowerHandler> getPostPowerHandler(Set<String> handlers);

    PowerErrorHandler getErrorHandler(String errorHandler);

    List<PowerChainHandler> getPowerChainHandler(Set<String> handlers);

    PowerInvokeCollector getPowerInvokeCollector(PowerInvokeCollectorContext collector);
}
