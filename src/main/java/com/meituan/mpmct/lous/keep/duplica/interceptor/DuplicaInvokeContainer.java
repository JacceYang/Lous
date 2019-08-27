package com.meituan.mpmct.lous.keep.duplica.interceptor;

import com.meituan.mpmct.lous.keep.duplica.support.MemCache;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 9:37 PM 2019/8/25
 **/
public class DuplicaInvokeContainer {

    private MemCache memCache;

    private DuplicaInvokeContext invokeContext;

    private DuplicaSourceContext sourceContext;

    public DuplicaInvokeContainer(MemCache memCache, DuplicaInvokeContext invokeContext, DuplicaSourceContext sourceContext) {
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
            memCache.putCache(invokeContext.getKey(), invokeContext.getContent(), sourceContext.getExpire());
        } else {
            return cacheValue.equals(invokeContext.getContent());
        }
        return false;
    }

    Object fastAck() throws IllegalAccessException, InstantiationException, NoSuchFieldException {

        Class<?> returnType = invokeContext.getReturnType();
        Object o = returnType.newInstance();

        if (invokeContext.getMsg() != null) {
            String[] fieldValue = getFieldValue(invokeContext.getMsg());
            Assert.isTrue(fieldValue.length == 2, "msg expression on duplica is invalid.");
            Field declaredField = returnType.getDeclaredField(fieldValue[0]);
            declaredField.setAccessible(true);
            declaredField.set(o, fieldValue[1]);
        }
        return o;
    }

    private String[] getFieldValue(String msg) {
        return StringUtils.split(msg, ":");
    }
}
