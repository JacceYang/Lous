package com.iyetoo.mpm.lous.cache.annotation;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 11:27 AM 2019/8/11
 **/
public enum CachingMode {
    LOCAL(1),
    REDIS(2);

    private Integer cacheLine;

    CachingMode(Integer cacheLine) {
        this.cacheLine = cacheLine;
    }

    public Integer getCacheLine() {
        return cacheLine;
    }
}
