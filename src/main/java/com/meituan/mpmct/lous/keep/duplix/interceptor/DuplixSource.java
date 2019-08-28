package com.meituan.mpmct.lous.keep.duplix.interceptor;

import org.springframework.beans.factory.BeanFactory;

import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 9:26 AM 2019/8/25
 **/
public interface DuplixSource {

    DuplixSourceContext getDuplixSourceContext(Method method, Object targetObject, Object[] parameters, BeanFactory beanFactory);
}
