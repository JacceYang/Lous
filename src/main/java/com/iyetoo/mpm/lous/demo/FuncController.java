package com.iyetoo.mpm.lous.demo;

import com.iyetoo.mpm.lous.keep.annotation.Duplix;
import com.iyetoo.mpm.lous.keep.annotation.Scene;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 11:58 AM 2019/8/25
 **/
@RestController
public class FuncController {


    @GetMapping("/demo/fun")
    @Duplix(scene = Scene.METHOD,key = "#name+#age",expire = 4,msg = "{\"msg\":\"提交太频繁!\",\"code\":400}")
    public ResponVo getName(String name,Integer age){
        return ResponVo.success(name+age);
    }
}
