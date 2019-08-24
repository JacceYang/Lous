package com.meituan.mpmct.lous.keep.power.interceptor;

import com.meituan.mpmct.lous.keep.power.support.RunStatus;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 1:36 PM 2019/8/19
 **/
public class DefaultPowerErrorHandler implements PowerErrorHandler {

    RunStatus status = new RunStatus();

    @Override
    public String getName() {
        return "default";
    }

    @Override
    public void error(Throwable throwable) {
        status.setError(throwable);
    }

    @Override
    public void message(String msg) {
        status.setMsg(msg);
    }

}
