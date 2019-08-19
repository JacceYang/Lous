package com.meituan.mpmct.lous.keep.ext;

import com.meituan.mpmct.lous.keep.interceptor.PrePowerHandler;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 1:13 PM 2019/8/19
 **/

public class AgeCheck extends PrePowerHandler {

    @Override
    public Object filter() {
        return null;
    }

    @Override
    public String getName() {
        return "age";
    }
}
