package com.iyetoo.mpm.lous.application;

import com.iyetoo.mpm.lous.application.demo.LoginService;
import com.iyetoo.mpm.lous.keep.annotation.Duplix;
import com.iyetoo.mpm.lous.keep.annotation.EnableKeep;
import com.iyetoo.mpm.lous.keep.annotation.Power;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.iyetoo.mpm"})
@EnableKeep(annotation = {Duplix.class, Power.class})
public class LousApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(LousApplication.class, args);
        LoginService loginServiceImpl = (LoginService) run.getBean("loginServiceImpl");
        loginServiceImpl.login("afewfsdfsdfs16位hex值，用字符串yangsdfsddfsfng", 15);
//        loginServiceImpl.login("afewfsdfsdfs16位hex值，用字符串yangsdfsddfsfng", 15);
        System.out.println("-------end---------");

    }
}
