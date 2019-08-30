package com.iyetoo.mpm.lous.keep.duplix.ext;

import com.iyetoo.mpm.lous.keep.duplix.support.MemCache;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 10:12 PM 2019/8/25
 **/
//@ConditionalOnMissingBean(value = MemCache.class
@Component
@ConditionalOnProperty(prefix = "lous",name = "duplix.enable",havingValue = "true",matchIfMissing =true)
public class LocalMemCache implements MemCache, SmartInitializingSingleton {

    /**
     * The key should be comparable and
     */
    private volatile Map<Object, WrappedValue> cache = new ConcurrentHashMap<>(16);

    @Value("${lous.duplix.cache.maxsize}")
    private String maxMemSize;

    @Value("${lous.duplix.cache.eliminate.step}")
    private Integer step;

    private LocalCacheMonitor monitor = null;

    private static String EMPTY_CONTENT="@@##$$";

    @Override
    public void putCache(Object key, String value, long ms,int times) {
        cache.put(key, new WrappedValue(value, ms,times, new Date().getTime()));
    }

    @Override
    public String getCache(Object key) {
        WrappedValue wrappedValue = cache.get(key);
        return wrappedValue == null ? null : WrappedValue.unWrappValue(wrappedValue);
    }

    @Override
    public void afterSingletonsInstantiated() {
        monitor = new LocalCacheMonitor(cache, maxMemSize, step);
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
         * add(in cache) time
         */
        private long in;

        /**
         * times can be invoke duration time.
         */
        private int times;

        private byte timeScene;

        public WrappedValue(String content, long ms, long in) {
            this(content, ms, 0, in);
        }

        public WrappedValue(String content, long ms,  int times,long in) {
            this.content = content;
            this.ms = ms;
            this.in = in;
            this.times = times-1;
            this.timeScene = (byte) (times > 0 ? 1 : 0);
        }

        public static String unWrappValue(WrappedValue value) {
            return value.getTimeScene() > 0?unWrappValueTime(value):unWrappValuePeriod(value);
        }

        private static String unWrappValuePeriod(WrappedValue value){
            if (value.getIn() + value.getMs() > new Date().getTime()) {
                return value.getContent();
            }
            return null;
        }

        private static String unWrappValueTime(WrappedValue value) {
            if (value.getTimes() > 0) {
                value.setTimes(value.getTimes() - 1);
            } else {
                return unWrappValuePeriod(value);
            }
            return EMPTY_CONTENT;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getMs() {
            return ms;
        }

        public long getIn() {
            return in;
        }

        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }

        public byte getTimeScene() {
            return timeScene;
        }

        @Override
        public int compareTo(WrappedValue o) {
            return ((this.ms + this.in) - (o.getMs() + o.getIn())) > 0 ? 1
                    : ((this.ms + this.in) - (o.getMs() + o.getIn())) == 0 ? 0 : -1;
        }
    }
}
