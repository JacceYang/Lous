package com.iyetoo.mpm.lous.keep.power.support;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 5:50 PM 2019/8/20
 **/
public class MutableMethodParameterValues implements MethodParameterValues, Iterable {

    List<PropertyValue> propertyValues;

    @Override
    public void addMethodValue(PropertyValue methodParameter) {
        if (propertyValues == null) {
            propertyValues = new ArrayList<>();
        }
        propertyValues.add(methodParameter);
    }

    @Override
    public void deleteMethodValue(String name) {
        if (propertyValues != null) {
            propertyValues.remove(getMethodValue(name));
        }
    }

    @Override
    public PropertyValue getMethodValue(String name) {
        if (propertyValues != null) {
            for (PropertyValue methodParameter : propertyValues) {
                methodParameter.getName().equals(name);
                return methodParameter;
            }
        }
        return null;
    }

    @Override
    public PropertyValue index(int index) {
        if (index > propertyValues.size() - 1) {
            throw new IndexOutOfBoundsException();
        }
        int count = 0;
        Iterator<PropertyValue> iterator = iterator();
        while (iterator.hasNext() && count < index) {
            count++;
            iterator.next();
        }

        return iterator.next();
    }

    public int getParameterCount() {
        return propertyValues == null ? 0 : propertyValues.size();
    }


    @Override
    public Iterator<PropertyValue> iterator() {
        return propertyValues.iterator();
    }
}
