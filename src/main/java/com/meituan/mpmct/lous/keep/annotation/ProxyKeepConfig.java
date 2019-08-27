package com.meituan.mpmct.lous.keep.annotation;

import com.meituan.mpmct.lous.keep.duplica.interceptor.BeanFactoryDuplicaAdvisor;
import com.meituan.mpmct.lous.keep.duplica.interceptor.DuplicaIntercepter;
import com.meituan.mpmct.lous.keep.duplica.interceptor.WebHandlerInterceptor;
import com.meituan.mpmct.lous.keep.power.interceptor.BeanFactoryPowerAdvisor;
import com.meituan.mpmct.lous.keep.power.interceptor.PowerInterceptor;
import com.meituan.mpmct.lous.keep.power.support.GlobalPowerHandlerRepository;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 10:21 PM 2019/8/18
 **/
@Configuration
public class ProxyKeepConfig extends AbstractKeepConfig {

    @Configuration    //todo 完善条件配置化逻辑
    public class ProxyDuplicaConfig {
        @Bean
        @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
        public DuplicaIntercepter duplicaIntercepter() {
            DuplicaIntercepter duplicaIntercepter = new DuplicaIntercepter();
            return duplicaIntercepter;
        }

        @Bean
        @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
        public BeanFactoryDuplicaAdvisor beanFactoryDuplicaAdvisor() {
            BeanFactoryDuplicaAdvisor beanFactoryDuplicaAdvisor = new BeanFactoryDuplicaAdvisor();
            beanFactoryDuplicaAdvisor.setAdvice(duplicaIntercepter());
            return beanFactoryDuplicaAdvisor;
        }

        @Bean
        @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
        public WebHandlerInterceptor webHandlerInterceptor() {
            WebHandlerInterceptor webHandlerInterceptor = new WebHandlerInterceptor();
            return webHandlerInterceptor;
        }
    }

    //@Configuration
    public class ProxyPowerConfig {

        @Bean
        @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
        public BeanFactoryPowerAdvisor beanFactoryPowerAdvisor() {
            BeanFactoryPowerAdvisor beanFactoryPowerAdvisor = new BeanFactoryPowerAdvisor();
            beanFactoryPowerAdvisor.setAdvice(powerInterceptor());
            return beanFactoryPowerAdvisor;
        }

        @Bean
        @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
        public PowerInterceptor powerInterceptor() {
            PowerInterceptor powerInterceptor = new PowerInterceptor();
            powerInterceptor.setGlobalPowerHandler(globalPowerHandlerRepository());
            return powerInterceptor;

        }

        @Bean
        @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
        public GlobalPowerHandlerRepository globalPowerHandlerRepository() {
            GlobalPowerHandlerRepository globalPowerHandlerRepository = new GlobalPowerHandlerRepository();
            return globalPowerHandlerRepository;
        }
    }
}
