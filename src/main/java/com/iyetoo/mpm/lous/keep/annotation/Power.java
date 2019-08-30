package com.iyetoo.mpm.lous.keep.annotation;

import com.iyetoo.mpm.lous.keep.power.interceptor.AbstractPostPowerHandler;
import com.iyetoo.mpm.lous.keep.power.interceptor.AbstractPrePowerHandler;
import com.iyetoo.mpm.lous.keep.power.interceptor.PowerErrorHandler;

import java.lang.annotation.*;

/**
 * Annotation indicating that the invoking of a method can be intercepted and do filter.
 *
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 6:02 PM 2019/8/11
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Power {


    /**
     * @return
     * INFO: before the invoke of method, some environment parameters may get from
     * a method invoke ,configuration properties,and so on. this collector can collect data
     * from those data source.
     * I don't want expose a array of collectors for one collector also can replace multiple ones  in logical.
     * NOTE :the collector currently support:
     * 1. class instance invoke #{this.methodName}
     * 2. Bean instance method invoke #{beanName.methodName}
     * the method should be public and currently not parameters method support
     * :
     * <p>
     * public foo(){
     * <p>
     * }.
     */
    String collector() default "";

    /**
     * The bean names of pre-handlers {@link AbstractPrePowerHandler} invoked before the advice method .
     * if the pre handler{@link AbstractPrePowerHandler#proceed()} return a false value. the pre-handler
     * behind will be invoked. the method and post-handler will not be invoke also.
     *
     * @return
     * NOTE  : The value define in preHandler[] indicates the call order of pre-handler.
     */
    String[] preHandler() default {};

    /**
     * The bean names of chains , currently not support.it designed just for scalability.
     *
     * @return
     */
    String[] chain() default {};

    /**
     * The bean names of post-handlers {@link AbstractPostPowerHandler} invoked before the advice method .
     * The post-handlers will be invoke after the method invoke ,if the method throw a exception. the
     * post-handler will not be invoke . Any handler in post-handlers return a false value when  call
     * {@link AbstractPostPowerHandler#proceed()} handler's behind will not be call.
     *
     * @return
     * NOTE  : The value define in preHandler[] indicates the call order of post-handler.
     */
    String[] postHandler() default {};

    /**
     * The bean name of error handler {@link PowerErrorHandler} invoked when a error/warn/info occ .
     *
     * @return
     */
    String errorHandler() default "";

}
