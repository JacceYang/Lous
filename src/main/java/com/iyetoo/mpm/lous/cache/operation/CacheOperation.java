package com.iyetoo.mpm.lous.cache.operation;

import com.iyetoo.mpm.lous.cache.annotation.CachingMode;

import java.util.LinkedHashSet;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 12:41 PM 2019/8/11
 **/
public class CacheOperation implements BaseCacheOperation {

    private String key;

    /**
     * 多级缓存时配置的不同介质名称
     */
    private String cacheName;

    private String keyGenerator;

    private LinkedHashSet<CachingMode> cachingModes;

    public CacheOperation() {
    }

    public CacheOperation(Builder builder) {
        this.key = builder.getKey();
        this.cacheName = builder.getCacheNames();
        this.keyGenerator = builder.getKeyGenerator();
        this.cachingModes = builder.getCachingModes();
    }

    @Override
    public String getCacheName() {
        return this.cacheName;
    }

    public String getKeyGenerator() {
        return keyGenerator;
    }

    public void setKeyGenerator(String keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

    public LinkedHashSet<CachingMode> getCachingModes() {
        return cachingModes;
    }

    public void setCachingModes(LinkedHashSet<CachingMode> cachingModes) {
        this.cachingModes = cachingModes;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public abstract static class Builder {

        private String key;

        private String cacheName;

        private String keyGenerator;

        private LinkedHashSet<CachingMode> cachingModes;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getCacheNames() {
            return cacheName;
        }

        public void setCacheNames(String name) {
            cacheName = name;
        }

        public String getKeyGenerator() {
            return keyGenerator;
        }

        public void setKeyGenerator(String keyGenerator) {
            this.keyGenerator = keyGenerator;
        }

        public LinkedHashSet<CachingMode> getCachingModes() {
            return cachingModes;
        }

        public void setCachingModes(CachingMode... modes) {
            cachingModes = new LinkedHashSet<>();
            for (int idx = 0; idx < modes.length; idx++) {
                this.cachingModes.add(modes[idx]);
            }
        }

        abstract CacheOperation build();
    }
}
