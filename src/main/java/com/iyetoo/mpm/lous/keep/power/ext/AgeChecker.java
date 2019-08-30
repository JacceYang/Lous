package com.iyetoo.mpm.lous.keep.power.ext;

import com.iyetoo.mpm.lous.keep.power.interceptor.AbstractPrePowerHandler;
import com.iyetoo.mpm.lous.keep.power.support.PowerInvokeContext;
import org.springframework.stereotype.Component;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 1:13 PM 2019/8/19
 **/
@Component
public class AgeChecker extends AbstractPrePowerHandler<Integer> {

    @Override
    public Object filter() {

        PowerInvokeContext context = getContext();

        context.getMethodParameterCount();
        String aDefault = context.getProperty("contry", String.class);
        criterion = context.getMethodParameter(1, Integer.class);

        if (criterion < 18) {

        }
        return criterion;
    }

    @Override
    public boolean proceed() {
        return criterion > 18;
    }

}
