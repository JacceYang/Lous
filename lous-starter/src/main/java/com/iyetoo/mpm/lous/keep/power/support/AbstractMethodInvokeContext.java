package com.iyetoo.mpm.lous.keep.power.support;

import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 5:34 PM 2019/8/20
 **/
public abstract class AbstractMethodInvokeContext implements MethodInvokeContext {

    private final Method method;

    private final Object[] arguments;

    private final ParameterNameDiscoverer parameterNameDiscoverer;

    MutableMethodParameterValues methodParameterValues = new MutableMethodParameterValues();

    private boolean argumentsLoaded = false;


    public AbstractMethodInvokeContext(Method method, Object[] arguments, ParameterNameDiscoverer parameterNameDiscoverer) {
        this.method = method;
        this.arguments = arguments;
        this.parameterNameDiscoverer = parameterNameDiscoverer;
    }

    @Override
    public Object[] getMethodParameters() {
        if (!argumentsLoaded) {
            lazyLoadArguments();
        }
        Object[] result = new Object[getMethodParameterCount()];
        int idx = 0;
        while (methodParameterValues.iterator().hasNext()) {
            PropertyValue next = methodParameterValues.iterator().next();
            result[idx++] = next.getValue();
        }
        return result;
    }

    @Override
    public int getMethodParameterCount() {
        if (!argumentsLoaded) {
            lazyLoadArguments();
        }
        return methodParameterValues.getParameterCount();
    }

    @Override
    public Object getMethodParameter(int index) {
        if (!argumentsLoaded) {
            lazyLoadArguments();
        }
        return methodParameterValues.index(index).getValue();
    }

    @Override
    public <T> T getMethodParameter(int index, Class<T> clazz) {
        Object result = getMethodParameter(index);
        return result == null ? null : (T) result;
    }

    protected void lazyLoadArguments() {
        // Shortcut if no args need to be loaded
        if (ObjectUtils.isEmpty(this.arguments)) {
            return;
        }

        String[] paramNames = this.parameterNameDiscoverer.getParameterNames(this.method);
        int paramCount = (paramNames != null ? paramNames.length : this.method.getParameterCount());
        int argsCount = this.arguments.length;

        for (int i = 0; i < paramCount; i++) {
            Object value = null;
            if (argsCount > paramCount && i == paramCount - 1) {
                // Expose remaining arguments as vararg array for last parameter
                value = Arrays.copyOfRange(this.arguments, i, argsCount);
            } else if (argsCount > i) {
                // Actual argument found - otherwise left as null
                value = this.arguments[i];
            }
            if (paramNames != null && paramNames[i] != null) {
                methodParameterValues.addMethodValue(new PropertyValue(paramNames[i], value));
            }
        }
        argumentsLoaded = true;
    }
}
