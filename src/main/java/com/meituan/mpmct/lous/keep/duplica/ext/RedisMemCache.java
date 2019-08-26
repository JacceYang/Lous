package com.meituan.mpmct.lous.keep.duplica.ext;

import com.meituan.mpmct.lous.keep.duplica.support.MemCache;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 10:12 PM 2019/8/25
 **/
public class RedisMemCache implements MemCache {

    @Override
    public void putCache(Object key, String value, long ms) {

    }

    @Override
    public String getCache(Object key) {
        return null;
    }
}
