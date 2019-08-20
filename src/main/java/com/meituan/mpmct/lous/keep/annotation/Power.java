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
     *
     * @return
     */
    String[] preHandler() default {};

    /**
     * 处理链
     *
     * @return
     */
    String[] chain() default {};

    /**
     * 后处理
     *
     * @return
     */
    String[] postHandler() default {};

    /**
     *  错误处理器
     * @return
     */
    String errorHandler() default "";

    /**
     * @INFO:
     *  before the invoke of method, some environment parameters may get from
     *  a method invoke ,configuration properties,and so on. this collector can collect data
     *  from those data source。
     *  I don't want expose a array of collectors for one collector also can replace multiple ones  in logical.
     *
     *  @NOTE :the collector currently support:
     *  1. class instance invoke #{this.methodName}
     *  2. Bean instance method invoke #{beanName.methodName}
     *  the method should be public .
     * @return
     */
    String collector() default "";

}
