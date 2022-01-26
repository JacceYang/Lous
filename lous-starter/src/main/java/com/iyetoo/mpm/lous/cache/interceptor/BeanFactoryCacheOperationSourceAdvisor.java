package com.iyetoo.mpm.lous.cache.interceptor;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 10:16 PM 2019/8/9
 **/
public class BeanFactoryCacheOperationSourceAdvisor extends AbstractBeanFactoryPointcutAdvisor {


    @Override
    public Pointcut getPointcut() {
        return new CacheOperationSourcePointcut();
    }
}
