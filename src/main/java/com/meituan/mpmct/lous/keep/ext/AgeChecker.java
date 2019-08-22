package com.meituan.mpmct.lous.keep.ext;

import com.meituan.mpmct.lous.keep.interceptor.AbstractPrePowerHandler;
import com.meituan.mpmct.lous.keep.support.PowerInvokeContext;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 1:13 PM 2019/8/19
 **/
@Component
public class AgeChecker extends AbstractPrePowerHandler<Integer> {

    @Override
    public Object filter() {

        PowerInvokeContext context = getContext();

        context.getMethodParameterCount();

        Set<String> allPropertiesName = context.getAllPropertiesName();
        String aDefault = context.getProperty("contry", String.class);
        invokeResult = context.getMethodParameter(1, Integer.class);
        return null;
    }

    @Override
    public boolean proceed() {
        return invokeResult > 18;
    }

    @Override
    public String getName() {
        return "age";
    }
}
