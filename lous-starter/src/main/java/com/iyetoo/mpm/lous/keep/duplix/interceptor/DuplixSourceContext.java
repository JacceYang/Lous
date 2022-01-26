package com.iyetoo.mpm.lous.keep.duplix.interceptor;

import java.util.Arrays;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 9:25 AM 2019/8/25
 **/
public class DuplixSourceContext {

    private RequestURI requestURI;

    private String key;

    private long expire;

    private int times;

    private RequestParameter parameter;

    private String msg;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class RequestParameter {
        private Object[] parameters;

        public RequestParameter(Object[] parameters) {
            this.parameters = parameters;
        }

        /**
         *  TODO  for Java Bean object which do not implement toString() method . it may cause error.
         * @return
         */
        @Override
        public String toString() {
            return "{" +
                      Arrays.toString(parameters) +
                    '}';
        }
    }
}
