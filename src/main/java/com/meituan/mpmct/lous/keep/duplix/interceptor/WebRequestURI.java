package com.meituan.mpmct.lous.keep.duplix.interceptor;

import com.meituan.mpmct.lous.keep.duplix.support.DuplixAnnotationUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 8:57 AM 2019/8/25
 **/
public class WebRequestURI implements RequestURI {

    private String webUrl;


    public WebRequestURI(HttpServletRequest request, String key) {
        this.webUrl = unWrapperUrl(request);
    }

    public WebRequestURI(Method method, Class<?> targetClass, String key) {
        this.webUrl = unWrapperUrl(method, targetClass);
    }

    private String unWrapperUrl(Method method, Class<?> targetClass) {
        String[] extractPath = DuplixAnnotationUtils.extractPath(method);
        Assert.notNull(extractPath, "more url configured");
        Assert.isTrue(extractPath != null && extractPath.length > 1, "unsupport more than one url in method");
        return extractPath[0];
    }

    private String unWrapperUrl(HttpServletRequest request) {
        String uri = request.getRequestURI();
        if (StringUtils.hasText(request.getContextPath())) {
            uri = uri.substring(request.getContextPath().length());
        }
        return uri;
    }


    @Override
    public Object anchor() {
        return webUrl;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebRequestURI that = (WebRequestURI) o;
        return Objects.equals(webUrl, that.webUrl);
    }

    @Override
    public int hashCode() {

        return Objects.hash(webUrl);
    }


}
