package com.meituan.mpmct.lous.keep.power.interceptor;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 12:00 PM 2019/8/23
 **/
public interface PrePowerHandler extends ConfigurablePowerHandler {

    /**
     * Power Handler name will be presented by Bean name if this method doesn't been
     * Override, Otherwise the sub class getName() method result will be the handler name.
     *
     * @return
     */
    @Override
    default String getName() {
        return null;
    }
}
