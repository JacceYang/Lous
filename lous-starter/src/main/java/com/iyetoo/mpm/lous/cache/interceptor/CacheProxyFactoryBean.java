package com.iyetoo.mpm.lous.cache.interceptor;

import org.springframework.aop.framework.AbstractSingletonProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 9:18 AM 2019/8/10
 **/
public class CacheProxyFactoryBean extends AbstractSingletonProxyFactoryBean {


    @Override
    protected Object createMainInterceptor() {
        return new DefaultPointcutAdvisor();
    }
}
