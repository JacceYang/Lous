package com.meituan.mpmct.lous.keep.duplix.interceptor;

/**
 * #{@link RequestURI} represent the request source type .
 * currently support method and url.
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 8:56 AM 2019/8/25
 **/
public interface RequestURI {

    Object anchor();
}
