package com.meituan.mpmct.lous.keep.support;

import com.meituan.mpmct.lous.keep.annotation.Power;
import com.meituan.mpmct.lous.keep.interceptor.*;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import sun.jvm.hotspot.utilities.Assert;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 5:08 PM 2019/8/19
 **/
public class PowerSourceParser {

    public PowerElement computePowerElement(Method method, Class<?> targetClass) {
        Method specifed = AopUtils.getMostSpecificMethod(method, targetClass);
        if (specifed != null) {
            if (specifed.isAnnotationPresent(Power.class)) {
                return parsePower(specifed);
            }
        }
        return null;
    }

    private PowerElement parsePower(Method method) {
        Set<Power> allMergedAnnotations = AnnotatedElementUtils.findAllMergedAnnotations(method, Power.class);

        if (allMergedAnnotations != null) {
            Assert.that(allMergedAnnotations.size() == 1, "multiple @power configure on method [" + method.getName() + "] is prohibit!!!");
            PowerElement.Builder builder = new PowerElement.Builder();
            allMergedAnnotations.stream().forEach(power -> {
                builder.setChains(power.chain());
                builder.setErrorHandler(power.errorHandler());
                builder.setPreHandlers(power.preHandler());
                builder.setPostHandlers(power.postHandler());
            });

            return builder.build();
        }
        return null;
    }

    public PowerSourceContext parsePowerSourceContext(GlobalPowerHandler globalPowerHandler,PowerElement powerElement){
        Assert.that(powerElement!=null,"parse powerElement equals null");
        PowerSourceContext sourceContext=new PowerSourceContext();

        if (!CollectionUtils.isEmpty(powerElement.getPostHandlers())) {
            List<PostPowerHandler> postPowerHandlers = globalPowerHandler.getPostPowerHandler(powerElement.getPostHandlers());
            sourceContext.setPostPowerHandlers(postPowerHandlers);
        }

        if (!CollectionUtils.isEmpty(powerElement.getPreHandlers())) {
            List<AbstractPrePowerHandler> abstractPrePowerHandlers = globalPowerHandler.getPrePowerHandler(powerElement.getPreHandlers());
            sourceContext.setPreHandlers(abstractPrePowerHandlers);
        }

        if (!CollectionUtils.isEmpty(powerElement.getChains())) {
            List<PowerChainHandler> powerChainHanlder = globalPowerHandler.getPowerChainHanlder(powerElement.getChains());
            sourceContext.setPowerChainHandlers(powerChainHanlder);
        }

        if (StringUtils.hasText(powerElement.getErrorHandler())){
            PowerErrorHandler errorHandler = globalPowerHandler.getErrorHandler(powerElement.getErrorHandler());
            sourceContext.setErrorHandler(errorHandler);
        }

        return sourceContext;
    }
}
