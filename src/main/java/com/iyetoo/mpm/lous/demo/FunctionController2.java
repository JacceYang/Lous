package com.iyetoo.mpm.lous.demo;

import com.iyetoo.mpm.lous.keep.annotation.Duplix;
import com.iyetoo.mpm.lous.keep.annotation.Scene;
import org.springframework.web.bind.annotation.*;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 3:28 PM 2019/8/25
 **/
@RestController
public class FunctionController2 {

    @PostMapping("/demo/fun2")
    @Duplix(scene = Scene.METHOD,key = "#person.id",times = 2,expire=15, msg = "提交太频繁!")
    public String getName(@RequestBody Person person){

        return person.getName()+person.getId();
    }
}
