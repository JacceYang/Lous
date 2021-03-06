package com.iyetoo.mpm.lous.keep.duplix.support;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 11:03 PM 2019/8/26
 **/
public class DuplixAnnotationUtils {

    public static String[] extractPath(Method method) {
        RequestMapping webRequest = AnnotatedElementUtils.getMergedAnnotation(method, RequestMapping.class);
        String baseUrl[] = null;
        if (method.getDeclaringClass().isAnnotationPresent(RequestMapping.class)) {
            RequestMapping classRequestMapping = method.getDeclaringClass().getAnnotation(RequestMapping.class);
            baseUrl = classRequestMapping.value();
        }
        String[] subUrl = webRequest.value();

        return combineUlr(baseUrl != null ? baseUrl[0] : "", subUrl);
    }

    private static String[] combineUlr(String base, String[] subUrl) {
        if (subUrl != null && subUrl.length > 0) {
            String[] path = new String[subUrl.length];
            for (int idx = 0; idx < subUrl.length; idx++) {
                StringBuilder builder = new StringBuilder(base);
                path[idx] = builder.append(subUrl[idx]).toString();
            }
            return path;
        }
        return null;
    }
}
