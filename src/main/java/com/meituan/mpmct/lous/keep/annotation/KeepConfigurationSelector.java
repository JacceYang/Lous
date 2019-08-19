package com.meituan.mpmct.lous.keep.annotation;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;
import org.springframework.context.annotation.AutoProxyRegistrar;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 6:49 PM 2019/8/11
 **/
public class KeepConfigurationSelector extends AdviceModeImportSelector<EnableKeep> {

    @Override
    protected String[] selectImports(AdviceMode adviceMode) {
        /// 这个地方也可以用 if 语句和 三目运算符号,switch 更加简介明了
        switch (adviceMode){
            case PROXY:
                return importProxy();
            case ASPECTJ:
                return importAspectJ();
            default:
                    return null;
        }
    }

    private String[] importProxy(){
        List<String> result=new ArrayList<>();

        result.add(AutoProxyRegistrar.class.getName());
        result.add(ProxyKeepConfig.class.getName());
//        result.add(ProxyDuplicaConfig.class.getName());

        return StringUtils.toStringArray(result);
    }

    private String[] importAspectJ(){

        throw new UnsupportedOperationException("当前还没有开发好,不支持!!!");
    }
}
