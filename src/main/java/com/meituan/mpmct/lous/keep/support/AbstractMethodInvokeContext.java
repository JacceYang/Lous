package com.meituan.mpmct.lous.keep.support;

import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 5:34 PM 2019/8/20
 **/
public abstract class AbstractMethodInvokeContext implements MethodInvokeContext {
    MultMethodParameterValues methodParameterValues = new MultMethodParameterValues();


    private final Method method;

    private final Object[] arguments;

    private final ParameterNameDiscoverer parameterNameDiscoverer;

    private boolean argumentsLoaded = false;


    public AbstractMethodInvokeContext(Method method, Object[] arguments, ParameterNameDiscoverer parameterNameDiscoverer) {
        this.method = method;
        this.arguments = arguments;
        this.parameterNameDiscoverer = parameterNameDiscoverer;
    }

    @Override
    public Object[] getMethodParameters() {
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
        return methodParameterValues.getParameterCount();
    }

    @Override
    public Object getMethodParameter(int index) {
        return methodParameterValues.index(index);
    }

    protected void lazyLoadArguments(){
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
            }
            else if (argsCount > i) {
                // Actual argument found - otherwise left as null
                value = this.arguments[i];
            }

        }
    }
}
