package com.meituan.mpmct.lous.keep.duplica.interceptor;

import com.meituan.mpmct.lous.keep.duplica.support.MemCache;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 10:10 PM 2019/8/24
 **/
public class DuplicaAspectSupport {

    private DuplicaSource duplicaSource=new AnnotationDuplicaSource();

    private DuplicaInvokeContextParser invokeContextParser=new DuplicaInvokeContextParser();

    private MemCache memCache;

    public Object execute(DuplicaInvoker invoker, Method method, Object targetObject, Object[] parameter) throws Throwable {

        // 1. 解析请求的元数据部分,确认请求来源
        DuplicaSourceContext duplicaSourceContext = duplicaSource.getDuplicaSourceContext(method, targetObject, parameter);

        // 2. 构建请求的实际上线文
        DuplicaInvokeContext invokeContext = invokeContextParser.parseInvokeContext(duplicaSourceContext);

        // 3. 检查请求是否
        DuplicaInvokeContainer invokeContainer=new DuplicaInvokeContainer(memCache,invokeContext,duplicaSourceContext );

        if (invokeContainer.runCheck()){
           return invokeContainer.fastAck();
        }

        return invoker.invoke();
    }

}
