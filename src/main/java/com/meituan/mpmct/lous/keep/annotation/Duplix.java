package com.meituan.mpmct.lous.keep.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * Annotation indicating that the  a frequently invoking of on method will be prevent .
 *
 * @Author:Yangchao16
 * @Description: duplicate submit or call.
 * @Data:Initialized in 6:01 PM 2019/8/11
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Duplix {

    /**
     * The scenario the {@link Duplix} used .
     * Supported scene defined in {@link Scene}
     *
     * @return
     */
    Scene scene() default Scene.WEB;

    /**
     * Spel define for generate id for request.
     * Same id will be treated as a same request.
     *
     * @return
     */
    String key() default "";

    /**
     * Time window unite define
     *
     * @return
     */
    TimeUnit unit() default TimeUnit.SECONDS;

    /**
     * Time window expire time.
     *
     * @return
     */
    int expire() default 5;

    /**
     * call times
     *
     * @return
     */
    int times() default 0;

    /**
     * failAck msg;
     *
     * @return
     */
    String msg() default "";

}
