package com.iyetoo.mpm.lous.cache.annotation;

import java.lang.annotation.*;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 11:21 AM 2019/8/11
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
     *
     * @return
     */
    String cacheName() default "";

    /**
     * the cache media ,you can define multi-level cache for you
     *
     * @return
     */
    CachingMode[] cacheMode() default {CachingMode.REDIS};


    /**
     * the cache key define ,
     *
     * @return
     */
    String keyGenerator() default "";

    /**
     * async if the async manner to put cache
     */
    boolean async() default false;

}
