package com.meituan.mpmct.lous.keep.duplica.interceptor;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 9:25 AM 2019/8/25
 **/
public class DuplicaSourceContext {

    private RequestURI requestURI;

    private String key;

    private long expire;

    private int times;

    private RequestParameter parameter;

    public RequestURI getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(RequestURI requestURI) {
        this.requestURI = requestURI;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public RequestParameter getParameters() {
        return parameter;
    }

    public void setParameters(RequestParameter parameter) {
        this.parameter = parameter;
    }

    public static class RequestParameter{
        private Object[] parameters;

        public RequestParameter(Object[] parameters) {
            this.parameters = parameters;
        }

        @Override
        public String toString() {
            return "{" +
                    "parameters=" + Arrays.toString(parameters) +
                    '}';
        }
    }
}
