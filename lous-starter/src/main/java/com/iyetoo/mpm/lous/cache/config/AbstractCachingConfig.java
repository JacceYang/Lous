package com.iyetoo.mpm.lous.cache.config;

import com.iyetoo.mpm.lous.cache.annotation.EnableSmartCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 7:56 PM 2019/8/9
 **/
@Configuration
public abstract class AbstractCachingConfig implements ImportAware {

    private AnnotationAttributes enableMCaching;

    @Override
    public void setImportMetadata(AnnotationMetadata annotationMetadata) {

        enableMCaching = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(EnableSmartCaching.class.getName(), false));

    }
}
