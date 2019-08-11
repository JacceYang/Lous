package com.meituan.mpmct.lous.keep.annotation;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 6:49 PM 2019/8/11
 **/
public class KeepConfigurationSelector extends AdviceModeImportSelector<EnableKeep> {

    @Override
    protected String[] selectImports(AdviceMode adviceMode) {

        return new String[0];
    }
}
