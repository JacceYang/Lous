package com.iyetoo.mpm.lous.keep.power.support;

import com.iyetoo.mpm.lous.keep.power.interceptor.BeanPowerInvokeCollector;
import com.iyetoo.mpm.lous.keep.power.interceptor.PowerInvokeCollector;
import com.iyetoo.mpm.lous.keep.power.interceptor.ThisPowerInvokeCollector;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 9:32 PM 2019/8/20
 **/
public class PowerInvokeCollectorParser {

    private static String TOKEN_THIS = "this";

    private static String TOKE_DOLLAR = "$";

    private static String TOKEN_PHOLDER_PRIX = "#";

    private static String TOKEN_LBACKET = "{";

    private static String TOKEN_RBACKET = "}";

    private static String TOKEN_DOM = ".";

    private static String TOKEN_AT = "@";

    private static String TOKEN_THIS_REG = "[#|$]\\{this.*}$";

    private static String TOKEN_BEAN_REG = "[#|$]\\{@\\w+.*}$";

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
        String methodName = findCandidate(context.getCollector(), TOKEN_DOM, TOKEN_RBACKET);
        if (!StringUtils.hasText(methodName)) {
            throw new IllegalArgumentException("Can't parse method name, expression [" + context.getCollector() + "]");
        }

        Method exeMethod = ClassUtils.getMethod(context.getTargetClass(), methodName);
        return new ThisPowerInvokeCollector(exeMethod, context.getTargetClass(), context.getTargetObject(), context.getParameters());
    }


    private PowerInvokeCollector calculateBeanExecutor(PowerInvokeCollectorContext context, BeanFactory beanFactory) {

        String beanName = findCandidate(context.getCollector(), TOKEN_AT, TOKEN_DOM);
        String methodName = findCandidate(context.getCollector(), TOKEN_DOM, TOKEN_RBACKET);
        if (!StringUtils.hasText(beanName) || !StringUtils.hasText(methodName)) {
            throw new IllegalArgumentException("Either method name or bean name parse error, expression [" + context.getCollector() + "]");
        }

        Object bean = beanFactory.getBean(beanName);
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(bean);
        Method exeMethod = ClassUtils.getMethod(targetClass, methodName);

        return new BeanPowerInvokeCollector(exeMethod, context.getTargetClass(), context.getTargetObject(), context.getParameters());
    }


    private String findCandidate(String source, String beginToken, String endToken) {
        int begin = source.indexOf(beginToken);
        int end = source.indexOf(endToken);
        return source.substring(begin + 1, end);
    }

}
