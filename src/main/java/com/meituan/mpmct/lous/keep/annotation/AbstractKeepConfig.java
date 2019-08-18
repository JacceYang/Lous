package com.meituan.mpmct.lous.keep.annotation;

import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 10:26 PM 2019/8/18
 **/
public abstract class AbstractKeepConfig implements ImportAware {

    private AnnotationAttributes annotationAttributes;

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        annotationAttributes=AnnotationAttributes.fromMap(importMetadata.getAnnotationAttributes(EnableKeep.class.getName(),false));
    }



}
