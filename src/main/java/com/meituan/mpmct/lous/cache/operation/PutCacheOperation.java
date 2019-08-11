package com.meituan.mpmct.lous.cache.operation;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 12:38 PM 2019/8/11
 **/
public class PutCacheOperation extends CacheOperation {

    private boolean async;

    public PutCacheOperation(Builder builder) {
        super(builder);
    }


    public static class Builder extends CacheOperation.Builder{
        private boolean async=false;

        public boolean isAsync() {
            return async;
        }

        public void setAsync(boolean async) {
            this.async = async;
        }

        @Override
        CacheOperation build() {

            return new PutCacheOperation(this);
        }
    }
}
