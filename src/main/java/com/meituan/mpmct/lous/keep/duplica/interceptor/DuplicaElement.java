package com.meituan.mpmct.lous.keep.duplica.interceptor;

import com.meituan.mpmct.lous.keep.annotation.Scene;

import java.util.concurrent.TimeUnit;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 8:45 AM 2019/8/25
 **/
public class DuplicaElement {

    private Scene scene;

    private String key;

    private TimeUnit unit;

    private int expire;

    private int times;
}
