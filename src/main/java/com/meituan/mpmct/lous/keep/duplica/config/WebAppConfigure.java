package com.meituan.mpmct.lous.keep.duplica.config;

import com.meituan.mpmct.lous.keep.duplica.interceptor.WebHandlerInterceptor;
import com.meituan.mpmct.lous.keep.event.ObservableEventCenter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 12:09 PM 2019/8/25
 **/
@Component
public class WebAppConfigure implements WebMvcConfigurer,Observer,InstantiationAwareBeanPostProcessor{

    private Set<String> includePath=new HashSet<>();


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (!CollectionUtils.isEmpty(includePath)){
            InterceptorRegistration interceptorRegistration = registry.addInterceptor(new WebHandlerInterceptor());
            interceptorRegistration.addPathPatterns(includePath.toArray(new String[0]));
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof DuplicaEvent){
            DuplicaEvent duplicaEvent= (DuplicaEvent) arg;
            String[] extractedPath = extractPath(duplicaEvent.getMethod());
            if (extractedPath!=null && extractedPath.length>0){
                CollectionUtils.mergeArrayIntoCollection(extractedPath,includePath);
            }
        }
    }


    private String[] extractPath(Method method){
        GetMapping methodAnnotation = method.getAnnotation(GetMapping.class);
        String baseUrl[]=null;
        if (method.getDeclaringClass().isAnnotationPresent(RequestMapping.class)) {
            RequestMapping classRequestMapping = method.getDeclaringClass().getAnnotation(RequestMapping.class);
            baseUrl=classRequestMapping.value();
        }
        String[] subUrl = methodAnnotation.value();

        return  combineUlr(baseUrl!=null?baseUrl[0]:"",subUrl);
    }

    private String[] combineUlr(String base,String[] subUrl ){
        if (subUrl!=null && subUrl.length>0){
            String[] path=new String[subUrl.length];
            for (int idx=0;idx<subUrl.length;idx++){
                StringBuilder builder=new StringBuilder(base);
                path[idx]=builder.append(subUrl[idx]).toString();
            }
            return path;
        }
        return null;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        ObservableEventCenter.addListener(this);
        return null;
    }
}
