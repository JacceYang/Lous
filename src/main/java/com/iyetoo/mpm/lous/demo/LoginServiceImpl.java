package com.iyetoo.mpm.lous.demo;

import com.iyetoo.mpm.lous.cache.annotation.CachingMode;
import com.iyetoo.mpm.lous.cache.annotation.GetCache;
import com.iyetoo.mpm.lous.keep.annotation.Duplix;
import com.iyetoo.mpm.lous.keep.annotation.Scene;
import com.iyetoo.mpm.lous.keep.power.support.PropertyValueWrapper;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 11:07 PM 2019/8/9
 **/
@Component
public class LoginServiceImpl implements LoginService {


    private AtomicInteger counter = new AtomicInteger(0);

      @GetCache(key = "#name+#age", cacheName = "login-record",cacheMode = CachingMode.LOCAL)
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
