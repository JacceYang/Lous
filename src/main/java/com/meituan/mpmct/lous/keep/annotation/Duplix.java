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
     * call times during period of expire() .
     * when it is set value. request during period of expire time can only call the times set
     * by this value.
     *
     * @return
     */
    int times() default 0;

    /**
     * failAck msg,when the request is prevented by {@link Duplix}, this msg can be return to the caller.
     *
     *  the msg format currently support:
     *  1.  response is primitive type, a default value will be return. so msg need not be set.
     *  2.  response is {@link String} , msg should be defined in format msg="request invalid, try later ."
     *  3.  response is JavaBean Object, msg should be defined in format msg="filed: your can only draw a lottery twice a day,see you tomorrow."
     * @return
     */
    String msg() default "";

}
