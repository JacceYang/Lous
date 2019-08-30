package com.iyetoo.mpm.lous.cache.annotation;

import java.lang.annotation.*;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 10:57 PM 2019/8/9
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface GetCache {

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

}
