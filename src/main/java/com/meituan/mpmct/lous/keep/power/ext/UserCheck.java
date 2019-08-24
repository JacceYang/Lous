package com.meituan.mpmct.lous.keep.power.ext;

import com.meituan.mpmct.lous.keep.power.interceptor.AbstractPrePowerHandler;
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
        criterion = true;
        return null;
    }

    @Override
    public boolean proceed() {
        return criterion;
    }

    @Override
    public String getName() {
        return "user";
    }
}
