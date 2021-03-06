package com.iyetoo.mpm.lous.keep.power.support;

import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 9:58 AM 2019/8/22
 **/
public abstract class AbstractPowerInvokeContext extends AbstractMethodInvokeContext implements PropertyInvokeContext {

    /**
     * It should be initialized with new or keep null.
     */
    protected List<PropertyValue> propertyValues = new ArrayList<>();

    /**
     * The property name's from propertyValues .
     */
    protected Set<String> propertyName = new LinkedHashSet<>();

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

    @Override
    public void addProperties(PropertyValue[] propertyValues) {

    }
}
