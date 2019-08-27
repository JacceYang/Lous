package com.meituan.mpmct.lous.keep.duplica.interceptor;

import com.meituan.mpmct.lous.keep.duplica.ext.RedisMemCache;
import com.meituan.mpmct.lous.keep.duplica.support.MemCache;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 9:37 PM 2019/8/25
 **/
public class DuplicaInvokeContainer {

    private MemCache  memCache;

    private DuplicaInvokeContext invokeContext;

    private DuplicaSourceContext sourceContext;

    public DuplicaInvokeContainer(MemCache memCache, DuplicaInvokeContext invokeContext, DuplicaSourceContext sourceContext) {
        this.memCache = memCache;
        this.invokeContext = invokeContext;
        this.sourceContext = sourceContext;
    }

    /**
     * if the content is in mem and the same with the  request .
     * @return
     */
    boolean runCheck(){
        String cacheValue = memCache.getCache(invokeContext.getKey());
        if (cacheValue==null){
            memCache.putCache(invokeContext.getKey(),invokeContext.getContent(),sourceContext.getExpire());
        }else {
           return cacheValue.equals(invokeContext.getContent());
        }
        return false;
    }

     <T>  T fastAck(){

        return (T)new Object();
    }
}
