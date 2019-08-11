package com.meituan.mpmct.lous.keep.annotation;

import java.lang.annotation.*;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 6:02 PM 2019/8/11
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Power {

    /**
     * 前处理
     * @return
     */
    String[] preHandler() default "";

    /**
     * 处理链
     * @return
     */
    String[] chain() default "";

    /**
     * 后处理
     * @return
     */
    String[] postHandler() default "";

}
