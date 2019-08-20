package com.meituan.mpmct.lous.keep.support;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 5:42 PM 2019/8/20
 **/
public class PropertyValue {

    /**
     * parameter name
     */
    private String name;

    /**
     * parameter value
     */
    private Object value;


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

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }
}
