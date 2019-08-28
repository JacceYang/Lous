package com.meituan.mpmct.lous.keep.duplix.interceptor;

import java.lang.reflect.Method;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 7:56 PM 2019/8/28
 **/
public class DuplixSourceParseContext {

    private Method method;
    private Class<?> targetClass;
    private Object[] parameters;
    private DuplixElement element;

    public DuplixSourceParseContext(Method method, Class<?> targetClass, Object[] parameters, DuplixElement element) {
        this.method = method;
        this.targetClass = targetClass;
        this.parameters = parameters;
        this.element = element;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public DuplixElement getElement() {
        return element;
    }

    public void setElement(DuplixElement element) {
        this.element = element;
    }
}
