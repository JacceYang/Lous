package com.meituan.mpmct.lous.keep.interceptor;

import com.meituan.mpmct.lous.keep.support.*;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 1:26 PM 2019/8/19
 **/
public class PowerAspectSupport implements SmartInitializingSingleton, BeanFactoryAware {


    private AnnotationPowerSource annotationPowerSource = new AnnotationPowerSource();

    private GlobalPowerHandler globalPowerHandler;

    private BeanFactory beanFactory;

    private ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
    /**
     * 可以后面提供扩展功能
     */
    private PowerHandlerRunContainer powerHandlerRunContainer = new PowerHandlerRunContainer();

    protected Object execute(PowerInvoker invoker, Method method, Object[] parameters, Object targetObject) throws IllegalAccessException, InstantiationException {

        PowerSourceContext powerSource = annotationPowerSource.getPowerSource(method, targetObject.getClass(), targetObject, parameters);

        if (powerSource==null) {
            return invoker.invoke();
        }

        //3 .构造请求的InvokerContext 为每一个 Handler
        PowerInvokeContext propertyInvokeContext = new DefaultPowerInvokeContext(getTargetMethod(method, targetObject.getClass()), parameters, parameterNameDiscoverer);
        if (propertyInvokeContext==null) {
            return invoker.invoke();
        }
        powerHandlerRunContainer.preRun(powerSource, propertyInvokeContext);

        //4. 执行 filter 逻辑
        Object invokeResult = null;
        if (powerHandlerRunContainer.canRun()) {
            invokeResult = invoker.invoke();
            powerHandlerRunContainer.afterRun(powerSource, propertyInvokeContext);
        }

        return safeReturn(invokeResult, method.getReturnType());
    }

    private Class<?> getTargetClass(Object target) {
        return AopProxyUtils.ultimateTargetClass(target);
    }

    private Method getTargetMethod(Method method, Class<?> targetClass) {
        return AopUtils.getMostSpecificMethod(method, targetClass);
    }

    public GlobalPowerHandler getGlobalPowerHandler() {
        return globalPowerHandler;
    }

    public void setGlobalPowerHandler(GlobalPowerHandler globalPowerHandler) {
        this.globalPowerHandler = globalPowerHandler;
    }

    @Override
    public void afterSingletonsInstantiated() {
        annotationPowerSource.setGlobalPowerHandler(globalPowerHandler);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    private Object safeReturn(Object obj, Class<?> returnType) {
        boolean primitive = returnType.isPrimitive();
        if (primitive && obj == null) {
            switch (returnType.getName()) {
                case "boolean":
                    return Boolean.FALSE;
                case "int":
                    return 0;
                case "float":
                    return 0.0f;
                case "double":
                    return 0.0d;
                case "bit":
                    return 0;
                default:
                    return 0;
            }
        }
        return obj;
    }
}
