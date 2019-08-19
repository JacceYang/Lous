package com.meituan.mpmct.lous.keep.support;

import com.meituan.mpmct.lous.keep.interceptor.PowerElement;
import org.springframework.core.MethodClassKey;

import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 6:59 PM 2019/8/19
 **/
public class AnnotationPowerSource implements PowerSource{

    private PowerHandlerManager powerHandlerManager=new PowerHandlerManager();

    private PowerSourceParser powerSourceParser=new PowerSourceParser();

    private GlobalPowerHandler globalPowerHandler;

    @Override
    public PowerSourceContext getPowerSource(Method method, Class<?> targetClass) {

        MethodClassKey methodClassKey=new MethodClassKey(method,targetClass);


        PowerElement powerElement = powerSourceParser.computePowerElement(method, targetClass);

        PowerSourceContext sourceContext = powerSourceParser.parsePowerSourceContext(globalPowerHandler, powerElement);

        return sourceContext;
    }

    public void setGlobalPowerHandler(GlobalPowerHandler globalPowerHandler) {
        this.globalPowerHandler = globalPowerHandler;
    }
}
