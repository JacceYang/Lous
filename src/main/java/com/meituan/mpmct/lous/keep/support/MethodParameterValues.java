package com.meituan.mpmct.lous.keep.support;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 5:47 PM 2019/8/20
 **/
public interface MethodParameterValues {


    void  addMethodValue(PropertyValue methodParameter);

    void  deleteMethodValue(String name);

    PropertyValue getMethodValue(String name);

    PropertyValue index(int index);

}
