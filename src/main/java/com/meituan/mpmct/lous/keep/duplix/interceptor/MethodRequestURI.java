package com.meituan.mpmct.lous.keep.duplix.interceptor;

import org.springframework.core.MethodClassKey;

import java.util.Objects;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 9:01 AM 2019/8/25
 **/
public class MethodRequestURI implements RequestURI {

    private MethodClassKey methodClassKey;

    public MethodRequestURI(MethodClassKey methodClassKey, String key) {
        this.methodClassKey = methodClassKey;
    }

    @Override
    public Object anchor() {
        return methodClassKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodRequestURI that = (MethodRequestURI) o;
        return Objects.equals(methodClassKey, that.methodClassKey);
    }

    @Override
    public int hashCode() {

        return Objects.hash(methodClassKey);
    }
}
