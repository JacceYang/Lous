package com.iyetoo.mpm.lous.cache.operation;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 12:47 PM 2019/8/11
 **/
public class GetCacheOperation extends CacheOperation {


    public GetCacheOperation(Builder builder) {
        super(builder);
    }

    public static class Builder extends CacheOperation.Builder {

        @Override
        CacheOperation build() {
            return new GetCacheOperation(this);
        }
    }
}
