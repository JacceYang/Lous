package com.meituan.mpmct.lous.keep.ext;

import com.meituan.mpmct.lous.keep.interceptor.AbstractPrePowerHandler;
import org.springframework.stereotype.Component;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 1:12 PM 2019/8/19
 **/
@Component
public class UserCheck extends AbstractPrePowerHandler<Boolean> {


    @Override
    public Object filter() {
        invokeResult=true;
        return null;
    }

    @Override
    public boolean proceed() {
        return invokeResult;
    }

    @Override
    public String getName() {
        return "user";
    }
}
