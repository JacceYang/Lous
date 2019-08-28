package com.meituan.mpmct.lous.demo;

import com.meituan.mpmct.lous.keep.annotation.Duplix;
import com.meituan.mpmct.lous.keep.annotation.Scene;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 3:28 PM 2019/8/25
 **/
@RestController
public class FunctionController2 {

    @PostMapping("/demo/fun2")
    @Duplix(scene = Scene.METHOD,key = "#person.id",times = 4,expire=15, msg = "提交太频繁!")
    public String getName(@RequestBody Person person){

        return person.getName()+person.getId();
    }
}
