package com.iyetoo.mpm.lous.keep.power.interceptor;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 1:03 PM 2019/8/19
 **/
public class BeanFactoryPowerAdvisor extends AbstractBeanFactoryPointcutAdvisor {

    @Override
    public Pointcut getPointcut() {
        return new PowerPointcut();
    }
}
