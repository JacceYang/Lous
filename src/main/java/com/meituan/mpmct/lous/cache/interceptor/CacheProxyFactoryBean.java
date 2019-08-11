package com.meituan.mpmct.lous.cache.interceptor;

import org.springframework.aop.framework.AbstractSingletonProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 9:18 AM 2019/8/10
 **/
public class CacheProxyFactoryBean extends AbstractSingletonProxyFactoryBean {


    @Override
    protected Object createMainInterceptor() {
        return new DefaultPointcutAdvisor();
    }
}
