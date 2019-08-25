package com.meituan.mpmct.lous.keep.duplica.interceptor;

import org.springframework.core.MethodClassKey;

import java.util.Objects;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 9:01 AM 2019/8/25
 **/
public class MethodRequestURI implements RequestURI{

    private MethodClassKey methodClassKey;

    private String key;

    public MethodRequestURI(MethodClassKey methodClassKey, String key) {
        this.methodClassKey = methodClassKey;
        this.key = key;
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
        MethodRequestURI that = (MethodRequestURI) o;
        return Objects.equals(methodClassKey, that.methodClassKey) &&
                Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(methodClassKey, key);
    }
}
