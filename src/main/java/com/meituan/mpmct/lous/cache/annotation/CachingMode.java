package com.meituan.mpmct.lous.cache.annotation;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 11:27 AM 2019/8/11
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
