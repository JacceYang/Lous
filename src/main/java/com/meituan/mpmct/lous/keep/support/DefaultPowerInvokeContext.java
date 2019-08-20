package com.meituan.mpmct.lous.keep.support;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 1:54 PM 2019/8/20
 **/
public class DefaultPowerInvokeContext extends AbstractMethodInvokeContext implements PowerInvokeContext, MethodInvokeContext {

    /**
     * It should be initialized with new or keep null.
     */
    List<PropertyValue> propertyValues = new ArrayList<>();

    /**
     *
     */
    Set<String> propertyName=new LinkedHashSet<>();

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
                return next;
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
}
