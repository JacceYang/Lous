package com.iyetoo.mpm.lous.keep.duplix.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.iyetoo.mpm.lous.keep.duplix.support.MemCache;
import jdk.nashorn.api.scripting.JSObject;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 9:37 PM 2019/8/25
 **/
public class DuplixInvokeContainer {

    private MemCache memCache;

    private DuplixInvokeContext invokeContext;

    private DuplixSourceContext sourceContext;

    public DuplixInvokeContainer(MemCache memCache, DuplixInvokeContext invokeContext, DuplixSourceContext sourceContext) {
        this.memCache = memCache;
        this.invokeContext = invokeContext;
        this.sourceContext = sourceContext;
    }

    /**
     * if the content is in mem and the same with the  request .
     *
     * @return
     */
    boolean runCheck() {
        String cacheValue = memCache.getCache(invokeContext.getKey());
        if (cacheValue == null) {
            memCache.putCache(invokeContext.getKey(), invokeContext.getContent(), sourceContext.getExpire(),sourceContext.getTimes());
        } else {
            return cacheValue.equals(invokeContext.getContent());
        }
        return false;
    }

    Object fastAck() throws IllegalAccessException, InstantiationException, NoSuchFieldException, IOException {

        Class<?> returnType = invokeContext.getReturnType();
        Object o = null;
        if (invokeContext.getMsg() != null && !isSampleType(returnType)) {
            o = JSONObject.parseObject(invokeContext.getMsg(), returnType);
        }
        if (o==null){
            o = returnType.newInstance();
        }
        return returnType.equals(String.class) ? invokeContext.getMsg() : o;
    }

    private boolean isSampleType(Class<?> clazz){
        return clazz.isPrimitive() || clazz.equals(String.class);
    }

    private String[] getFieldValue(String msg) {
        return StringUtils.split(msg, ":");
    }
}
