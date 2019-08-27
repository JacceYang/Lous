package com.meituan.mpmct.lous.keep.duplica.ext;

import com.meituan.mpmct.lous.keep.duplica.support.MemCache;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 10:12 PM 2019/8/25
 **/
@Component
public class LocalMemCache implements MemCache {


    /**
     * The key should be comparable and
     */
    Map<Object, Object> cache = new HashMap<>(16);


    @Override
    public void putCache(Object key, String value, long ms) {
        cache.put(key, new WrappedValue(value, ms, new Date().getTime()));
    }

    @Override
    public String getCache(Object key) {
        WrappedValue wrappedValue = (WrappedValue) cache.get(key);

        return wrappedValue == null ? null : WrappedValue.unWrappValue(wrappedValue);
    }

    public static class WrappedValue {

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
    }
}
