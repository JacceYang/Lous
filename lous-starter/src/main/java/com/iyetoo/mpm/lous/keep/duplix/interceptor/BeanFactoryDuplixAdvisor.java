package com.iyetoo.mpm.lous.keep.duplix.interceptor;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 10:57 PM 2019/8/24
 **/
public class BeanFactoryDuplixAdvisor extends AbstractBeanFactoryPointcutAdvisor {
    @Override
    public Pointcut getPointcut() {
        return new DuplixPointcut();
    }
}
