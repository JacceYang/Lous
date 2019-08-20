package com.meituan.mpmct.lous.keep.support;

import com.meituan.mpmct.lous.keep.interceptor.PostPowerHandler;
import com.meituan.mpmct.lous.keep.interceptor.AbstractPrePowerHandler;
import org.springframework.util.CollectionUtils;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 8:50 PM 2019/8/19
 **/
public class PowerHandlerRunContainer {

   private int status;

   public void preRun(PowerSourceContext powerSourceContext, PowerInvokeContext context){
      if (!CollectionUtils.isEmpty(powerSourceContext.getPreHandlers())) {
         for (AbstractPrePowerHandler abstractPrePowerHandler :powerSourceContext.getPreHandlers()){
            abstractPrePowerHandler.setContext(context);
            abstractPrePowerHandler.filter();
            if (!abstractPrePowerHandler.proceed()) {
               abstractPrePowerHandler.getErrorHandler().message("处理失败！");
               status=1;
            }
         }
      }
      status=0;
   }

   public void afterRun(PowerSourceContext powerSourceContext,PowerInvokeContext context){

      if (!CollectionUtils.isEmpty(powerSourceContext.getPostPowerHandlers())) {
         for (PostPowerHandler postPowerHandler:powerSourceContext.getPostPowerHandlers()){
            postPowerHandler.setContext(context);
            postPowerHandler.filter();
            if (!postPowerHandler.proceed()){
               postPowerHandler.getErrorHandler().message("处理失败!");
               status=1;
            }
         }
      }
      status=0;
   }

   public boolean canRun(){
      return status==0;
   }
}
