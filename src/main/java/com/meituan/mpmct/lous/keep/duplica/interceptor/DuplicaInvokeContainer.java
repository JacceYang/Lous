package com.meituan.mpmct.lous.keep.duplica.interceptor;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 9:37 PM 2019/8/25
 **/
public class DuplicaInvokeContainer {


    boolean runCheck(){

        return true;
    }

     <T>  T fastAck(){

        return (T)new Object();
    }
}
