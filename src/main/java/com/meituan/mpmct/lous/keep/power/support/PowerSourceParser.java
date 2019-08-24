package com.meituan.mpmct.lous.keep.power.support;

import com.meituan.mpmct.lous.keep.annotation.Power;
import com.meituan.mpmct.lous.keep.power.interceptor.*;
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
        Method specified = AopUtils.getMostSpecificMethod(method, targetClass);
        if (specified != null) {
            if (specified.isAnnotationPresent(Power.class)) {
                return parsePower(specified);
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
                builder.setCollector(power.collector());
            });

            return builder.build();
        }
        return null;
    }

    public PowerSourceContext parsePowerSourceContext(PowerSourceParserContext sourceParserContext) {
        Assert.that(sourceParserContext.getPowerElement() != null, "parse powerElement equals null");
        PowerSourceContext sourceContext = new PowerSourceContext();

        if (!CollectionUtils.isEmpty(sourceParserContext.getPowerElement().getPostHandlers())) {
            List<PostPowerHandler> postPowerHandlers = sourceParserContext.getGlobalPowerHandler()
                    .getPostPowerHandler(sourceParserContext.getPowerElement().getPostHandlers());
            sourceContext.setPostPowerHandlers(postPowerHandlers);
        }

        if (!CollectionUtils.isEmpty(sourceParserContext.getPowerElement().getPreHandlers())) {
            List<PrePowerHandler> abstractPrePowerHandlers = sourceParserContext.getGlobalPowerHandler()
                    .getPrePowerHandler(sourceParserContext.getPowerElement().getPreHandlers());
            sourceContext.setPreHandlers(abstractPrePowerHandlers);
        }

        if (!CollectionUtils.isEmpty(sourceParserContext.getPowerElement().getChains())) {
            List<PowerChainHandler> powerChainHanlder = sourceParserContext.getGlobalPowerHandler()
                    .getPowerChainHandler(sourceParserContext.getPowerElement().getChains());
            sourceContext.setPowerChainHandlers(powerChainHanlder);
        }

        if (StringUtils.hasText(sourceParserContext.getPowerElement().getErrorHandler())) {
            PowerErrorHandler errorHandler = sourceParserContext.getGlobalPowerHandler()
                    .getErrorHandler(sourceParserContext.getPowerElement().getErrorHandler());
            sourceContext.setErrorHandler(errorHandler);
        }

        if (StringUtils.hasText(sourceParserContext.getPowerElement().getCollector())) {
            PowerInvokeCollector powerInvokeCollector = sourceParserContext.getGlobalPowerHandler().getPowerInvokeCollector(sourceParserContext.getCollectorContext());
            sourceContext.setInvokeCollector(powerInvokeCollector);
        }

        return sourceContext;
    }


    public static class PowerSourceParserContext {
        private GlobalPowerHandler globalPowerHandler;
        private PowerElement powerElement;
        private PowerInvokeCollectorContext collectorContext;

        public PowerSourceParserContext(GlobalPowerHandler globalPowerHandler, PowerElement powerElement, PowerInvokeCollectorContext collectorContext) {
            this.globalPowerHandler = globalPowerHandler;
            this.powerElement = powerElement;
            this.collectorContext = collectorContext;
        }

        public GlobalPowerHandler getGlobalPowerHandler() {
            return globalPowerHandler;
        }

        public void setGlobalPowerHandler(GlobalPowerHandler globalPowerHandler) {
            this.globalPowerHandler = globalPowerHandler;
        }

        public PowerElement getPowerElement() {
            return powerElement;
        }

        public void setPowerElement(PowerElement powerElement) {
            this.powerElement = powerElement;
        }

        public PowerInvokeCollectorContext getCollectorContext() {
            return collectorContext;
        }

        public void setCollectorContext(PowerInvokeCollectorContext collectorContext) {
            this.collectorContext = collectorContext;
        }
    }
}
