package com.meituan.mpmct.lous.keep.duplix.interceptor;

import com.meituan.mpmct.lous.keep.duplix.support.DefaultInvokeContentEncode;

import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 9:33 PM 2019/8/25
 **/
public class DuplixInvokeContextParser {

    DefaultInvokeContentEncode defaultInvokeContentEncode = new DefaultInvokeContentEncode();

    public DuplixInvokeContext parseInvokeContext(Method method, DuplixSourceContext duplixSourceContext) {
        DuplixInvokeContext invokeContext = new DuplixInvokeContext();

        invokeContext.setAnchor(duplixSourceContext.getRequestURI().anchor().toString());
        invokeContext.setContent(defaultInvokeContentEncode.encode(duplixSourceContext.getParameters().toString()));
        invokeContext.setKey(duplixSourceContext.getKey());
        invokeContext.setReturnType(method.getReturnType());
        invokeContext.setMsg(duplixSourceContext.getMsg());

        return invokeContext;
    }
}
