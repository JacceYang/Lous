package com.meituan.mpmct.lous.cache.ext;

import com.meituan.mpmct.lous.cache.Cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 9:15 PM 2019/8/13
 **/
public class LocalCache implements Cache {

    private ConcurrentMap caches = new ConcurrentHashMap(16);

    public static Cache  NoCache=new LocalCache();


    @Override
    public Object getValue(Object key) {
        return caches.get(key);
    }

    @Override
    public <T> T getValue(Object key, Class<T> type) {
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        return caches.put(key, value);
    }

    @Override
    public Object pubIfAbsent(Object key, Object value) {
        return caches.put(key, value);
    }

    @Override
    public void clear() {

    }
}
