package com.meituan.mpmct.lous;

import com.meituan.mpmct.lous.demo.LoginService;
import com.meituan.mpmct.lous.keep.annotation.Duplica;
import com.meituan.mpmct.lous.keep.annotation.EnableKeep;
import com.meituan.mpmct.lous.keep.annotation.Power;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableKeep(annotation = {Duplica.class, Power.class})
public class LousApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(LousApplication.class, args);
        LoginService loginServiceImpl = (LoginService) run.getBean("loginServiceImpl");
        loginServiceImpl.login("afewfsdfsdfs16位hex值，用字符串yangsdfsddfsfng", 15);
        System.out.println("-------end---------");

    }
}
