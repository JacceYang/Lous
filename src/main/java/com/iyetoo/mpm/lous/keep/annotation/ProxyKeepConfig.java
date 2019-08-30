package com.iyetoo.mpm.lous.keep.annotation;

import com.iyetoo.mpm.lous.keep.duplix.interceptor.BeanFactoryDuplixAdvisor;
import com.iyetoo.mpm.lous.keep.duplix.interceptor.DuplixIntercepter;
import com.iyetoo.mpm.lous.keep.duplix.interceptor.WebHandlerInterceptor;
import com.iyetoo.mpm.lous.keep.power.interceptor.BeanFactoryPowerAdvisor;
import com.iyetoo.mpm.lous.keep.power.interceptor.PowerInterceptor;
import com.iyetoo.mpm.lous.keep.power.support.GlobalPowerHandlerRepository;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 10:21 PM 2019/8/18
 **/
@Configuration
public class ProxyKeepConfig extends AbstractKeepConfig {

    @Configuration
    @ConditionalOnProperty(prefix = "lous",name = "duplix.enable",havingValue = "true",matchIfMissing = true)
    public class ProxyDuplixConfig {
        @Bean
        @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
        public DuplixIntercepter duplicaIntercepter() {
            DuplixIntercepter duplicaIntercepter = new DuplixIntercepter();
            return duplicaIntercepter;
        }

        @Bean
        @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
        public BeanFactoryDuplixAdvisor beanFactoryDuplixAdvisor() {
            BeanFactoryDuplixAdvisor beanFactoryDuplixAdvisor = new BeanFactoryDuplixAdvisor();
            beanFactoryDuplixAdvisor.setAdvice(duplicaIntercepter());
            return beanFactoryDuplixAdvisor;
        }

        @Bean
        @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
        public WebHandlerInterceptor webHandlerInterceptor() {
            WebHandlerInterceptor webHandlerInterceptor = new WebHandlerInterceptor();
            return webHandlerInterceptor;
        }
    }

    @Configuration
    @ConditionalOnProperty(prefix = "lous",name = "power.enable",havingValue = "true")
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
