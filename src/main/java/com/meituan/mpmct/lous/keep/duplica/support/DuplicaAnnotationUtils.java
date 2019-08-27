package com.meituan.mpmct.lous.keep.duplica.support;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 11:03 PM 2019/8/26
 **/
public class DuplicaAnnotationUtils {

    public static String[] extractPath(Method method){
        GetMapping methodAnnotation = method.getAnnotation(GetMapping.class);
        String baseUrl[]=null;
        if (method.getDeclaringClass().isAnnotationPresent(RequestMapping.class)) {
            RequestMapping classRequestMapping = method.getDeclaringClass().getAnnotation(RequestMapping.class);
            baseUrl=classRequestMapping.value();
        }
        String[] subUrl = methodAnnotation.value();

        return  combineUlr(baseUrl!=null?baseUrl[0]:"",subUrl);
    }

    private static String[] combineUlr(String base,String[] subUrl ){
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
}
