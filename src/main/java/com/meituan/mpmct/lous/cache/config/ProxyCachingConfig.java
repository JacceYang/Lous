package com.meituan.mpmct.lous.cache.config;

import com.meituan.mpmct.lous.cache.interceptor.BeanFactoryCacheOperationSourceAdvisor;
import com.meituan.mpmct.lous.cache.interceptor.CacheInterceptor;
import com.meituan.mpmct.lous.cache.operation.AnnotationCacheOperationParser;
import com.meituan.mpmct.lous.cache.operation.AnnotationCacheOperationSource;
import com.meituan.mpmct.lous.cache.operation.CacheOperationSource;
import com.meituan.mpmct.lous.cache.support.CacheManagerSolver;
import com.meituan.mpmct.lous.cache.support.CacheManagerSolverSupport;
import com.meituan.mpmct.lous.cache.support.CacheSolver;
import com.meituan.mpmct.lous.cache.support.CacheSolverSupport;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 7:59 PM 2019/8/9
 **/
@Configuration
public class ProxyCachingConfig extends AbstractCachingConfig {

    @Bean(name = CacheManagementConfigUtils.CACHE_ADVISOR_BEAN_NAME)
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public BeanFactoryCacheOperationSourceAdvisor cacheAdvisor() {
        BeanFactoryCacheOperationSourceAdvisor advisor = new
                BeanFactoryCacheOperationSourceAdvisor();
        advisor.setAdvice(cacheInterceptor());
        return advisor;
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public CacheOperationSource cacheOperationSource() {
        return new AnnotationCacheOperationSource(new AnnotationCacheOperationParser());
    }


    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public CacheInterceptor cacheInterceptor() {
        CacheInterceptor cacheInterceptor = new CacheInterceptor();
        cacheInterceptor.setCacheOperationSource(cacheOperationSource());
        cacheInterceptor.setCacheManagerSolver(cacheManagerSolver());
        cacheInterceptor.setCacheSolver(cacheSolver());
        return cacheInterceptor;
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public CacheManagerSolver cacheManagerSolver() {
        CacheManagerSolver cacheManagerSolver = new CacheManagerSolverSupport();
        return cacheManagerSolver;
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public CacheSolver cacheSolver() {
        CacheSolver cacheSolver = new CacheSolverSupport();
        return cacheSolver;

    }
}
