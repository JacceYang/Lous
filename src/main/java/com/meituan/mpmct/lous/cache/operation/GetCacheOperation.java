package com.meituan.mpmct.lous.cache.operation;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 12:47 PM 2019/8/11
 **/
public class GetCacheOperation extends CacheOperation {


    public GetCacheOperation(Builder builder) {
        super(builder);
    }

    public static class Builder extends CacheOperation.Builder{

        @Override
        CacheOperation build() {
            return new GetCacheOperation(this);
        }
    }
}
