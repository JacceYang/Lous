package com.meituan.mpmct.lous.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 3:28 PM 2019/8/25
 **/
@RestController
public class FunctionController2 {

    @GetMapping("/demo/fun2")
    public String getName(String name,Integer age){

        return name+age;
    }
}
