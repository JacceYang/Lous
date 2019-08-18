package com.meituan.mpmct.lous.keep.annotation;

import com.meituan.mpmct.lous.keep.interceptor.DuplicaIntercepter;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 10:32 PM 2019/8/18
 **/
@Configuration
public class ProxyDuplicaConfig {

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public DuplicaIntercepter duplicaIntercepter(){
        DuplicaIntercepter duplicaIntercepter=new DuplicaIntercepter();

        return duplicaIntercepter;
    }


}
