package com.meituan.mpmct.lous.keep.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author:Yangchao16
 * @Description: duplicate submit or call.
 * @Data:Initialized in 6:01 PM 2019/8/11
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Duplica {

    /**
     * 分为web 层和 service 层两种场景
     *
     * @return
     */
    Scene scene() default Scene.WEB;

    /**
     * Spel 动态介些的key
     *
     * @return
     */
    String key() default "";

    /**
     * 时间窗口单位
     *
     * @return
     */
    TimeUnit unit() default TimeUnit.SECONDS;

    /**
     * 时间窗口长度
     *
     * @return
     */
    int expire() default 5;

}
