package com.meituan.mpmct.lous.keep.power.interceptor;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 1:03 PM 2019/8/19
 **/
public class BeanFactoryPowerAdvisor extends AbstractBeanFactoryPointcutAdvisor {

    @Override
    public Pointcut getPointcut() {
        return new PowerPointcut();
    }
}
