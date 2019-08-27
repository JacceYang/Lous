package com.meituan.mpmct.lous.demo;

import com.meituan.mpmct.lous.keep.annotation.Duplica;
import com.meituan.mpmct.lous.keep.annotation.Scene;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 11:58 AM 2019/8/25
 **/
@RestController
//@RequestMapping("brand/")
public class FuncController {


    @GetMapping("/demo/fun")
    @Duplica(scene = Scene.METHOD,key = "#name+#age",expire = 4,msg = "msg:提交太频繁!")
    public ResponVo getName(String name,Integer age){
        return ResponVo.success(name+age);
    }
}
