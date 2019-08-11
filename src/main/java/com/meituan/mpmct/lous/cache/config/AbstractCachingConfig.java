package com.meituan.mpmct.lous.cache.config;

import com.meituan.mpmct.lous.cache.annotation.EnableMCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 7:56 PM 2019/8/9
 **/
@Configuration
public abstract class AbstractCachingConfig  implements ImportAware{

    private AnnotationAttributes enableMCaching;

    @Override
    public void setImportMetadata(AnnotationMetadata annotationMetadata) {

        enableMCaching = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(EnableMCaching.class.getName(),false));

        System.out.println(annotationMetadata);
    }
}
