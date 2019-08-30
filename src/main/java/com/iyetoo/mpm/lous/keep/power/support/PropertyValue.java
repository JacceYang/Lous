package com.iyetoo.mpm.lous.keep.power.support;

import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 5:42 PM 2019/8/20
 **/
public class PropertyValue implements Serializable {

    /**
     * parameter name
     */
    private String name;

    /**
     * parameter value
     */
    private Object value;


    public PropertyValue(String name, Object value) {
        Assert.hasText(name, "invalid input property name");
        Assert.notNull(value, "invalid input property value");
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return name.hashCode() * 29 + ObjectUtils.nullSafeHashCode(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof PropertyValue)) {
            return false;
        }

        PropertyValue other = (PropertyValue) obj;
        return this.name.equals(other.getName()) && this.value.equals(other.getValue());
    }
}
