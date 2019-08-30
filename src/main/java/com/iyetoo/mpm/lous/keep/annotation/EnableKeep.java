package com.iyetoo.mpm.lous.keep.annotation;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 6:46 PM 2019/8/11
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(KeepConfigurationSelector.class)
public @interface EnableKeep {

    boolean proxyTargetClass() default false;

    AdviceMode mode() default AdviceMode.PROXY;

    Class<? extends Annotation>[] annotation() default {};

}
