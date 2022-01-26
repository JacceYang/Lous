package com.iyetoo.mpm.lous.keep.power.support;

import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 1:54 PM 2019/8/20
 **/
public class DefaultPowerInvokeContext extends AbstractPowerInvokeContext implements PowerInvokeContext {

    public DefaultPowerInvokeContext(Method method, Object[] arguments, ParameterNameDiscoverer parameterNameDiscoverer) {
        super(method, arguments, parameterNameDiscoverer);
    }

    @Override
    public void addProperty(String name, Object value) {
        Assert.hasText(name, "name can't be blank");
        Assert.notNull(value, "value can't be null");
        propertyValues.add(new PropertyValue(name, value));
        propertyName.add(name);
    }

    @Override
    public Object getProperty(String name) {
        while (propertyValues.iterator().hasNext()) {
            PropertyValue next = propertyValues.iterator().next();
            if (next.getName().equals(name)) {
                return next.getValue();
            }
        }
        return null;
    }

    @Override
    public <T> T getProperty(String name, Class<T> clazz) {
        Object property = getProperty(name);
        return property == null ? null : (T) property;
    }

    @Override
    public Set<String> getAllPropertiesName() {
        return propertyName;
    }

    @Override
    public void addProperties(PropertyValue[] propertyValues) {
        if (propertyValues != null) {
            for (PropertyValue propertyValue : propertyValues) {
                this.propertyValues.add(propertyValue);
            }
        }
    }
}
