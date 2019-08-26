package com.meituan.mpmct.lous.keep.duplica.interceptor;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 10:57 PM 2019/8/24
 **/
public class BeanFactoryDuplicaAdvisor extends AbstractBeanFactoryPointcutAdvisor{
    @Override
    public Pointcut getPointcut() {
        return new DuplicaPointcut();
    }
}
