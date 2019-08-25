package com.meituan.mpmct.lous.keep.duplica.interceptor;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 8:57 AM 2019/8/25
 **/
public class WebRequestURI implements RequestURI{

    private String webUrl;

    private String key;

    public WebRequestURI(HttpServletRequest request, String key) {
        this.webUrl = unWrapperUrl(request);
        this.key = key;
    }

    private String unWrapperUrl(HttpServletRequest request){
        String uri=request.getRequestURI();
        if (StringUtils.hasText(request.getContextPath())) {
            uri=uri.substring(request.getContextPath().length());
        }
        return uri;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String key() {
        return getKey();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebRequestURI that = (WebRequestURI) o;
        return Objects.equals(webUrl, that.webUrl) &&
                Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {

        return Objects.hash(webUrl, key);
    }
}
