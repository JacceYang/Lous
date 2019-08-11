package com.meituan.mpmct.lous.cache.annotation;

import java.lang.annotation.*;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 11:21 AM 2019/8/11
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface PutCache {

    /**
     * SpEL expression for computing the key dynamically.
     */
    String key() default "";

    /**
     * the cache name for store the key
     * @return
     */
    String[] cacheName() default {};

    /**
     * the cache media ,you can define multi-level cache for you
     * @return
     */
    CachingMode[] cacheMode() default {CachingMode.REDIS};


    /**
     *  the cache key define ,
     * @return
     */
    String keyGenerator() default "";

    /**
     * async if the async manner to put cache
     */
    boolean async() default false;

}
