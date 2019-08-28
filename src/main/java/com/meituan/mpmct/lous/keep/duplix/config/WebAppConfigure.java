package com.meituan.mpmct.lous.keep.duplix.config;

import com.meituan.mpmct.lous.keep.duplix.interceptor.WebHandlerInterceptor;
import com.meituan.mpmct.lous.keep.duplix.support.DuplixAnnotationUtils;
import com.meituan.mpmct.lous.keep.event.KeepEventCenter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 12:09 PM 2019/8/25
 **/
@Component
public class WebAppConfigure implements WebMvcConfigurer, Observer, InstantiationAwareBeanPostProcessor {

    private Set<String> includePath = new HashSet<>();

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (!CollectionUtils.isEmpty(includePath)) {
            InterceptorRegistration interceptorRegistration = registry.addInterceptor(new WebHandlerInterceptor());
            interceptorRegistration.addPathPatterns(includePath.toArray(new String[0]));
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof DuplixEvent) {
            DuplixEvent duplixEvent = (DuplixEvent) arg;
            String[] extractedPath = DuplixAnnotationUtils.extractPath(duplixEvent.getMethod());
            if (extractedPath != null && extractedPath.length > 0) {
                CollectionUtils.mergeArrayIntoCollection(extractedPath, includePath);
            }
        }
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        KeepEventCenter.addListener(this);
        return null;
    }
}
