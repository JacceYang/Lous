package com.meituan.mpmct.lous.demo;

import com.meituan.mpmct.lous.cache.annotation.GetCache;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 11:07 PM 2019/8/9
 **/
@Component
public class LoginServiceImpl implements LoginService {


    private AtomicInteger counter=new AtomicInteger(0);

    @GetCache(key = "#name+#age",cacheName = "login-record")
    @Override
    public void login(String name, Integer age) {
        System.out.println(name+age +counter.incrementAndGet());
    }
}
