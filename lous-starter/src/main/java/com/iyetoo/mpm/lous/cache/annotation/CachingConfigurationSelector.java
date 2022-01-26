package com.iyetoo.mpm.lous.cache.annotation;

import com.iyetoo.mpm.lous.cache.config.ProxyCachingConfig;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;
import org.springframework.context.annotation.AutoProxyRegistrar;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 6:14 PM 2019/8/9
 **/
public class CachingConfigurationSelector extends AdviceModeImportSelector<EnableSmartCaching> {

    @Override
    protected String[] selectImports(AdviceMode adviceMode) {
        switch (adviceMode) {
            case PROXY:
                return getProxyImports();
            case ASPECTJ:
                return getAspectJImport();
            default:
                return null;
        }
    }


    private String[] getProxyImports() {
        List<String> result = new ArrayList<>();
        result.add(AutoProxyRegistrar.class.getName());
        result.add(ProxyCachingConfig.class.getName());


        return StringUtils.toStringArray(result);
    }

    private String[] getAspectJImport() {
        List<String> result = new ArrayList<>();

        return StringUtils.toStringArray(result);
    }
}
