package com.meituan.mpmct.lous.keep.support;

import com.sun.org.apache.xml.internal.serializer.Method;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 9:32 PM 2019/8/20
 **/
public class PowerInvokeCollectorParser {

     private static String TOKEN_THIS="[#|$]\\{this.*";

     private static String TOKEN_BEAN="[#|$]\\{@\\w+.*";

     public PowerInvokeCollector parseInvokeCollector(PowerInvokeCollectorContext context, BeanFactory beanFactory){

         if (!StringUtils.hasText(context.getCollector())){
             return null;
         }
         if (Pattern.matches(TOKEN_THIS, context.getCollector())){
             return new ThisPowerInvokeCollector(context.getMethod(),context.getTargetClass());
         }
         if (Pattern.matches(TOKEN_BEAN,context.getCollector())){
             return new BeanPowerInvokeCollector(context.getMethod(),context.getTargetClass(),beanFactory);
         }
         throw  new IllegalArgumentException("invalid expression ["+context.getCollector()+"]");
     }


    public static void main(String[] args) {

        if (Pattern.matches(TOKEN_THIS, "${@bean.")){
            System.out.println("hha");
        }

    }
}
