package com.iyetoo.mpm.lous.keep.power.interceptor;

import com.iyetoo.mpm.lous.keep.power.support.RunStatus;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 1:36 PM 2019/8/19
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
