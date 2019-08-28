package com.meituan.mpmct.lous.demo;

import com.meituan.mpmct.lous.keep.annotation.Duplix;
import com.meituan.mpmct.lous.keep.annotation.Scene;
import com.meituan.mpmct.lous.keep.power.support.PropertyValueWrapper;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 11:07 PM 2019/8/9
 **/
@Component
public class LoginServiceImpl implements LoginService {


    private AtomicInteger counter = new AtomicInteger(0);

    //    @GetCache(key = "#name+#age", cacheName = "login-record",cacheMode = CachingMode.LOCAL)
    @Override
    //@Power(preHandler = {"ageChecker", "user"}, collector = "#{@loginServiceImpl.country}")
    @Duplix(scene = Scene.METHOD,key = "#name+#age",expire = 10)
    public boolean login(String name, Integer age) {
        if (name.contains("yang")) {
            return true;
        }
        return false;
    }

    public Object country() {
        Object us = PropertyValueWrapper.wrapValue(new String[]{"contry"}, "US");
        return us;
    }
}
