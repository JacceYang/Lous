package com.iyetoo.mpm.lous.keep.power.support;

import java.util.Set;

/**
 * This Power Invoke context provide a invoke context for PowerHandler.
 *
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 12:20 PM 2019/8/20
 **/
public interface PropertyInvokeContext {

    /**
     * add property to powerInvokeContext for next use.
     *
     * @param name
     * @param value
     */
    void addProperty(String name, Object value);

    /**
     * add properties values one time.
     *
     * @param propertyValues
     */
    void addProperties(PropertyValue[] propertyValues);

    /**
     * get the property with name name
     *
     * @param name
     * @return
     */
    Object getProperty(String name);

    /**
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T getProperty(String name, Class<T> clazz);


    /**
     * Get all the properties name
     *
     * @return
     */
    Set<String> getAllPropertiesName();

}
