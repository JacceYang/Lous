package com.meituan.mpmct.lous.keep.support;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 9:32 PM 2019/8/20
 **/
public class PowerInvokeCollectorParser {

    private static String TOKEN_THIS = "this";

    private static String TOKE_DOLLAR = "$";

    private static String TOKEN_PHOLDER_PRIX = "#";

    private static String TOKEN_LBACKET = "{";

    private static String TOKEN_RBACKET = "}";

    private static String TOKEN_DOM = ".";

    private static String TOKEN_AT = "@";

    private static String TOKEN_THIS_REG = "[#|$]\\{this.*";

    private static String TOKEN_BEAN_REG = "[#|$]\\{@\\w+.*";

    public PowerInvokeCollector parseInvokeCollector(PowerInvokeCollectorContext context, BeanFactory beanFactory) {

        if (!StringUtils.hasText(context.getCollector())) {
            return null;
        }
        if (Pattern.matches(TOKEN_THIS_REG, context.getCollector())) {
            return calculateThisExecutor(context);
        }
        if (Pattern.matches(TOKEN_BEAN_REG, context.getCollector())) {
            return calculateBeanExecutor(context, beanFactory);
        }
        throw new IllegalArgumentException("invalid expression [" + context.getCollector() + "]");
    }

    private PowerInvokeCollector calculateThisExecutor(PowerInvokeCollectorContext context) {


        String collector = context.getCollector();
        int begin = collector.indexOf(TOKEN_DOM);
        int end = collector.indexOf(TOKEN_RBACKET);
        String methodName = collector.substring(begin + 1, end);
        Method exeMethod = ClassUtils.getMethod(context.getTargetClass(), methodName);

        return new ThisPowerInvokeCollector(exeMethod, context.getTargetClass(), context.getTargetObject(), context.getParameters());
    }


    private PowerInvokeCollector calculateBeanExecutor(PowerInvokeCollectorContext context, BeanFactory beanFactory) {

        String collector = context.getCollector();
        int begin = collector.indexOf(TOKEN_AT);
        int end = collector.indexOf(TOKEN_DOM);
        String beanName = collector.substring(begin, end);

        Object bean = beanFactory.getBean(beanName);

        return new BeanPowerInvokeCollector(context.getMethod(), context.getTargetClass(), context.getTargetObject(), context.getParameters(), beanFactory);
    }


}
