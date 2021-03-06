package com.iyetoo.mpm.lous.keep.power.interceptor;

import com.iyetoo.mpm.lous.keep.power.support.PowerInvokeContext;
import com.iyetoo.mpm.lous.keep.power.support.PowerSourceContext;
import com.iyetoo.mpm.lous.keep.power.support.PropertyValue;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 8:50 PM 2019/8/19
 **/
public class PowerHandlerRunContainer {

    /******************************************* Run status define ****************************************/
    private int status = 0;

    public void preRun(PowerSourceContext powerSourceContext, PowerInvokeContext context) {
        collect(powerSourceContext, context);
        Assert.notNull(context, "run context be null");
        if (!CollectionUtils.isEmpty(powerSourceContext.getPreHandlers())) {
            Iterator<PrePowerHandler> iterator = powerSourceContext.getPreHandlers().iterator();
            run(iterator, context);
        }
    }

    public void afterRun(PowerSourceContext powerSourceContext, PowerInvokeContext context) {
        Assert.notNull(context, "run context be null");
        if (!CollectionUtils.isEmpty(powerSourceContext.getPostPowerHandlers())) {
            Iterator<PostPowerHandler> iterator = powerSourceContext.getPostPowerHandlers().iterator();

            run(iterator, context);
        }
    }

    private void collect(PowerSourceContext powerSourceContext, PowerInvokeContext context) {

        try {
            Object collect = powerSourceContext.getInvokeCollector().collect();
            if (collect == null) {
                return;
            }
            if (collect instanceof PropertyValue[]) {
                context.addProperties((PropertyValue[]) collect);
            } else {
                context.addProperty("default", collect);
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private <T extends ConfigurablePowerHandler> void run(Iterator<T> iterator, PowerInvokeContext context) {
        while (canRun() && iterator.hasNext()) {
            ConfigurablePowerHandler postPowerHandler = iterator.next();
            postPowerHandler.setContext(context);
            postPowerHandler.filter();
            if (!postPowerHandler.proceed()) {
                postPowerHandler.getErrorHandler().message("no proceed ！");
                stop();
            }
        }
    }

    private void stop() {
        status = 1;
    }

    public boolean canRun() {
        return status == 0;
    }
}
