package com.meituan.mpmct.lous.keep.duplix.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 10:09 AM 2019/8/25
 **/
public class WebHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        return true;
    }
}
