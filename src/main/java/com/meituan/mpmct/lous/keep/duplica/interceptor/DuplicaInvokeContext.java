package com.meituan.mpmct.lous.keep.duplica.interceptor;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 9:02 PM 2019/8/25
 **/
public class DuplicaInvokeContext {

    /**
     * the request againts object。
     */
    private String anchor;

    /**
     * the request invoker id generate by rule .
     */
    private String key;

    /**
     * the request content used to just the same request.
     */
    private String content;

    public String getAnchor() {
        return anchor;
    }

    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}