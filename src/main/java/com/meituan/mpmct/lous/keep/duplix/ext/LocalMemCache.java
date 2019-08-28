package com.meituan.mpmct.lous.keep.duplix.ext;

import com.meituan.mpmct.lous.keep.duplix.support.MemCache;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 10:12 PM 2019/8/25
 **/
//@ConditionalOnMissingBean(value = MemCache.class
@Component
public class LocalMemCache implements MemCache,SmartInitializingSingleton{

    /**
     * The key should be comparable and
     */
    private volatile Map<Object, WrappedValue> cache = new ConcurrentHashMap<>(16);

    @Value("${lous.duplica.cache.maxsize}")
    private String maxMemSize;

    @Value("${lous.duplica.cache.step}")
    private Integer step;

    private LocalCacheMonitor monitor =null;

    @Override
    public void putCache(Object key, String value, long ms) {
        cache.put(key, new WrappedValue(value, ms, new Date().getTime()));
    }

    @Override
    public String getCache(Object key) {
        WrappedValue wrappedValue =cache.get(key);
        return wrappedValue == null ? null : WrappedValue.unWrappValue(wrappedValue);
    }

    @Override
    public void afterSingletonsInstantiated() {
        monitor = new LocalCacheMonitor(cache,maxMemSize,step);
    }

    public static class WrappedValue implements Comparable<WrappedValue> {

        /**
         * store content
         */
        private String content;

        /**
         * keep time
         */
        private long ms;

        /**
         * add(in) time
         */
        private long in;

        public WrappedValue(String content, long ms, long in) {
            this.content = content;
            this.ms = ms;
            this.in = in;
        }

        public static String unWrappValue(WrappedValue value) {
            if (value.getIn() + value.getMs() > new Date().getTime()) {
                return value.getContent();
            }
            return null;
        }

        public String getContent() {
            return content;
        }

        public long getMs() {
            return ms;
        }

        public long getIn() {
            return in;
        }


        @Override
        public int compareTo(WrappedValue o) {
            return ((this.ms + this.in) - (o.getMs() + o.getIn())) > 0 ? 1
                    : ((this.ms + this.in) - (o.getMs() + o.getIn())) == 0 ? 0 : -1;
        }
    }
}
