package com.meituan.mpmct.lous.keep.support;

import com.meituan.mpmct.lous.keep.interceptor.PowerInvoker;
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
public class PowerAspectSupport implements SmartInitializingSingleton,BeanFactoryAware{


    private AnnotationPowerSource annotationPowerSource=new AnnotationPowerSource();

    private GlobalPowerHandler globalPowerHandler;

    private BeanFactory beanFactory;

    private ParameterNameDiscoverer parameterNameDiscoverer= new DefaultParameterNameDiscoverer();
    /**
     * 可以后面提供扩展功能
     */
    private PowerHandlerRunContainer powerHandlerRunContainer=new PowerHandlerRunContainer();

    private PowerInvokeCollectorParser powerInvokeCollectorParser=new PowerInvokeCollectorParser();

    protected Object execute(PowerInvoker invoker, Method method,Object[] parameters,Object targetObject){



        // 1. 依据请求的 方法 ，获取@Power 注解的具体内容

        PowerSourceContext powerSource = annotationPowerSource.getPowerSource(method, targetObject.getClass(),targetObject);



        // 2. 解析 @Power 注解的内容为系统能够理解的,求解上下文环境

        //3 .构造请求的InvokerContext 为每一个 Handler
        PowerInvokeContext propertyInvokeContext =new DefaultPowerInvokeContext(getTargetMethod(method,targetObject.getClass()),parameters,parameterNameDiscoverer);
        powerHandlerRunContainer.preRun(powerSource, propertyInvokeContext);


        //4. 执行 filter 逻辑
        Object invokeResult=null;
        if (powerHandlerRunContainer.canRun()){
            invokeResult = invoker.invoke();
            powerHandlerRunContainer.afterRun(powerSource, propertyInvokeContext);
        }

        return invokeResult;
    }



    private Class<?> getTargetClass(Object target) {
        return AopProxyUtils.ultimateTargetClass(target);
    }

    private Method getTargetMethod(Method method,Class<?> targetClass){
        return AopUtils.getMostSpecificMethod(method,targetClass);
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
        this.beanFactory=beanFactory;
    }
}
