package com.iyetoo.mpm.lous.keep.power.support;

import com.iyetoo.mpm.lous.keep.power.interceptor.PowerElement;

import java.lang.reflect.Method;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 6:59 PM 2019/8/19
 **/
public class AnnotationPowerSource implements PowerSource {

    private PowerSourceParser powerSourceParser = new PowerSourceParser();

    private GlobalPowerHandler globalPowerHandler;

    @Override
    public PowerSourceContext getPowerSource(Method method, Class<?> targetClass, Object targetObject, Object[] parameters) {

        PowerElement powerElement = powerSourceParser.computePowerElement(method, targetClass);
        if (powerElement == null) {
            return null;
        }

        PowerInvokeCollectorContext collectorContext = new PowerInvokeCollectorContext(powerElement.getCollector(), method, targetClass, targetObject, parameters);
        PowerSourceParser.PowerSourceParserContext powerSourceParserContext = new PowerSourceParser.PowerSourceParserContext(globalPowerHandler, powerElement, collectorContext);

        PowerSourceContext sourceContext = powerSourceParser.parsePowerSourceContext(powerSourceParserContext);

        return sourceContext;
    }

    public void setGlobalPowerHandler(GlobalPowerHandler globalPowerHandler) {
        this.globalPowerHandler = globalPowerHandler;
    }
}
