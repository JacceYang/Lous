package com.meituan.mpmct.lous.keep.duplica.support;

import com.meituan.mpmct.lous.keep.annotation.Duplica;
import com.meituan.mpmct.lous.keep.annotation.Scene;
import com.meituan.mpmct.lous.keep.duplica.interceptor.DuplicaElement;
import com.meituan.mpmct.lous.keep.duplica.interceptor.DuplicaSourceContext;
import com.meituan.mpmct.lous.keep.duplica.interceptor.MethodRequestURI;
import com.meituan.mpmct.lous.keep.duplica.interceptor.WebRequestURI;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.MethodClassKey;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 10:15 AM 2019/8/26
 **/
public class DuplicaSourceContextParser {



    public DuplicaElement computeDuplicaElement(Method method, Class<?> targetClass) {
        Method mostSpecificMethod = AopUtils.getMostSpecificMethod(method, targetClass);
        if (mostSpecificMethod != null && mostSpecificMethod.isAnnotationPresent(Duplica.class)) {
            return parseDuplicaElement(mostSpecificMethod);
        }
        return null;
    }

    private DuplicaElement parseDuplicaElement(Method method) {
        Set<Duplica> allMergedAnnotations = AnnotatedElementUtils.findAllMergedAnnotations(method, Duplica.class);

        if (allMergedAnnotations != null) {
            Assert.isTrue(allMergedAnnotations.size() == 1, "more than one Duplica annotation define on method is prohibit!!!");
            DuplicaElement.Builder builder = new DuplicaElement.Builder();
            Duplica next = allMergedAnnotations.iterator().next();

            builder.setExpire(next.expire());
            builder.setKey(next.key());
            builder.setTimes(next.times());
            builder.setScene(next.scene());
            builder.setUnit(next.unit());
            return builder.build();
        }
        return null;
    }

    public DuplicaSourceContext parseDuplicaSourceContext(Method method,Class<?> targetClass,DuplicaElement element){

        Assert.notNull(element,"duplicate element shouldn't be null");
        DuplicaSourceContext sourceContext=new DuplicaSourceContext();
        String storeKey=null;

        if (element.getScene()== Scene.WEB){
            sourceContext.setRequestURI(new WebRequestURI(method,targetClass,element.getKey()));
        }else {
            sourceContext.setRequestURI(new MethodRequestURI(new MethodClassKey(method,targetClass),storeKey));
        }
        long ms= element.getUnit().convert(element.getExpire(),TimeUnit.MILLISECONDS);
        sourceContext.setExpire(ms);
        sourceContext.setKey(storeKey);
        sourceContext.setTimes(element.getTimes());
        return sourceContext;
    }

}