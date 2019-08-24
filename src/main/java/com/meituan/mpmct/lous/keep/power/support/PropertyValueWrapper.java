package com.meituan.mpmct.lous.keep.power.support;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 2:08 PM 2019/8/22
 **/
public final class PropertyValueWrapper {

    public static Object wrapValue(String[] names, Object... values) {

        if (names != null && values != null && (names.length == values.length)) {
            PropertyValue[] propertyValues = new PropertyValue[names.length];
            for (int idx = 0; idx < names.length; idx++) {
                propertyValues[idx] = new PropertyValue(names[idx], values[idx]);
            }
            return propertyValues;
        }

        throw new IllegalArgumentException("Either names or values is null or they are different size ");
    }
}
