package com.iyetoo.mpm.lous.keep.power.support;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 5:47 PM 2019/8/20
 **/
public interface MethodParameterValues {

    void addMethodValue(PropertyValue methodParameter);

    void deleteMethodValue(String name);

    PropertyValue getMethodValue(String name);

    PropertyValue index(int index);

}
