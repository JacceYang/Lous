package com.meituan.mpmct.lous.keep.duplica.interceptor;

import com.meituan.mpmct.lous.keep.duplica.support.DefaultInvokeContentEncode;

import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 9:33 PM 2019/8/25
 **/
public class DuplicaInvokeContextParser {

    DefaultInvokeContentEncode defaultInvokeContentEncode = new DefaultInvokeContentEncode();

    public DuplicaInvokeContext parseInvokeContext(Method method, DuplicaSourceContext duplicaSourceContext) {

        DuplicaInvokeContext invokeContext = new DuplicaInvokeContext();
        invokeContext.setAnchor(duplicaSourceContext.getRequestURI().anchor().toString());
        invokeContext.setContent(defaultInvokeContentEncode.encode(duplicaSourceContext.getParameters().toString()));
        invokeContext.setKey(duplicaSourceContext.getKey());
        invokeContext.setReturnType(method.getReturnType());
        invokeContext.setMsg(duplicaSourceContext.getMsg());
        return invokeContext;

    }
}
