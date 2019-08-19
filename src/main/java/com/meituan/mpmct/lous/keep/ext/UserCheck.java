package com.meituan.mpmct.lous.keep.ext;

import com.meituan.mpmct.lous.keep.interceptor.PrePowerHandler;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 1:12 PM 2019/8/19
 **/
public class UserCheck extends PrePowerHandler {


    @Override
    public Object filter() {
        return null;
    }

    @Override
    public String getName() {
        return "user";
    }
}
