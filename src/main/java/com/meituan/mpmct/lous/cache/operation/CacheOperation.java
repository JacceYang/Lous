package com.meituan.mpmct.lous.cache.operation;

import com.meituan.mpmct.lous.cache.annotation.CachingMode;

import java.util.*;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 12:41 PM 2019/8/11
 **/
public class CacheOperation implements BaseCacheOperation {

    private String key;

    private Set<String> cacheNames;

    private String keyGenerator;

    private List<CachingMode> cachingModes;

    public CacheOperation() {
    }

    public CacheOperation(Builder builder) {
        this.key = builder.getKey();
        this.cacheNames = builder.getCacheNames();
        this.keyGenerator = builder.getKeyGenerator();
        this.cachingModes = builder.getCachingModes();
    }

    @Override
    public Set<String> getCacheNames() {
        return this.cacheNames;
    }


    public abstract static class Builder {

        private String key;

        private Set<String> cacheNames;

        private String keyGenerator;

        private List<CachingMode> cachingModes;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Set<String> getCacheNames() {
            return cacheNames;
        }

        public void setCacheNames(String... names) {
            cacheNames=new LinkedHashSet();
            for (int idx = 0; idx < names.length; idx++) {
                this.cacheNames.add(names[idx]);
            }
        }

        public String getKeyGenerator() {
            return keyGenerator;
        }

        public void setKeyGenerator(String keyGenerator) {
            this.keyGenerator = keyGenerator;
        }

        public List<CachingMode> getCachingModes() {
            return cachingModes;
        }

        public void setCachingModes(CachingMode... modes) {
            cachingModes=new ArrayList<>();
            for (int idx = 0; idx < modes.length; idx++) {
                this.cachingModes.add(modes[idx]);
            }
        }

        abstract CacheOperation build();
    }
}
