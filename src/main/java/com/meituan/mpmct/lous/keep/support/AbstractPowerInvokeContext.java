package com.meituan.mpmct.lous.keep.support;

import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 9:58 AM 2019/8/22
 **/
public abstract class AbstractPowerInvokeContext extends AbstractMethodInvokeContext implements PropertyInvokeContext {


    public AbstractPowerInvokeContext(Method method, Object[] arguments, ParameterNameDiscoverer parameterNameDiscoverer) {
        super(method, arguments, parameterNameDiscoverer);
    }

    @Override
    public void addProperty(String name, Object value) {

    }

    @Override
    public Object getProperty(String name) {
        return null;
    }

    @Override
    public <T> T getProperty(String name, Class<T> clazz) {
        return null;
    }

    @Override
    public Set<String> getAllPropertiesName() {
        return null;
    }
}
