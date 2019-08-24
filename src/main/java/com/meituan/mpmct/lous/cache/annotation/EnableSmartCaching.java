package com.meituan.mpmct.lous.cache.annotation;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 6:16 PM 2019/8/9
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CachingConfigurationSelector.class)
public @interface EnableSmartCaching {

    boolean proxyTargetClass() default false;

    AdviceMode mode() default AdviceMode.PROXY;
}
