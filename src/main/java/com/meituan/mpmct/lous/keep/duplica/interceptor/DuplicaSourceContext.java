package com.meituan.mpmct.lous.keep.duplica.interceptor;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 9:25 AM 2019/8/25
 **/
public class DuplicaSourceContext {

    private RequestURI requestURI;

    private String key;

    private TimeUnit timeUnit;

    private int expire;

    private int times;

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

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
