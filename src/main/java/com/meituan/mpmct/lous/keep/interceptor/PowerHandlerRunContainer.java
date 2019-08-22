package com.meituan.mpmct.lous.keep.interceptor;

import com.meituan.mpmct.lous.keep.support.PowerInvokeContext;
import com.meituan.mpmct.lous.keep.support.PowerSourceContext;
import com.meituan.mpmct.lous.keep.support.PropertyValue;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 8:50 PM 2019/8/19
 **/
public class PowerHandlerRunContainer {

    private int status = 0;

    private void collect(PowerSourceContext powerSourceContext, PowerInvokeContext context) {

        try {
            Object collect = powerSourceContext.getInvokeCollector().collect();
            if (collect==null) {
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

    public void preRun(PowerSourceContext powerSourceContext, PowerInvokeContext context) {
        collect(powerSourceContext, context);
        Assert.notNull(context,"run context be null");
        if (!CollectionUtils.isEmpty(powerSourceContext.getPreHandlers())) {

            Iterator<AbstractPrePowerHandler> iterator = powerSourceContext.getPreHandlers().iterator();
            while (canRun()&&iterator.hasNext()){
                AbstractPrePowerHandler abstractPrePowerHandler=iterator.next() ;
                abstractPrePowerHandler.setContext(context);
                abstractPrePowerHandler.filter();
                if (!abstractPrePowerHandler.proceed()) {
                    abstractPrePowerHandler.getErrorHandler().message("no proceed ！");
                    stop();
                }
            }
        }
    }

    public void afterRun(PowerSourceContext powerSourceContext, PowerInvokeContext context) {
        Assert.notNull(context,"run context be null");
        if (!CollectionUtils.isEmpty(powerSourceContext.getPostPowerHandlers())) {

            for (PostPowerHandler postPowerHandler : powerSourceContext.getPostPowerHandlers()) {
                postPowerHandler.setContext(context);
                postPowerHandler.filter();
                if (!postPowerHandler.proceed()) {
                    postPowerHandler.getErrorHandler().message("no proceed ！");
                    stop();
                }
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
