package com.meituan.mpmct.lous.cache;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 5:37 PM 2019/8/11
 **/
public interface Cache {

    /**
     * @param key
     * @return
     */
    Object getValue(Object key);

    /**
     * @param key
     * @param type
     * @param <T>
     * @return
     */
    <T> T getValue(Object key, Class<T> type);

    /**
     * @param key
     * @param value
     * @return
     */
    Object put(Object key, Object value);

    /**
     * @param key
     * @param value
     * @return
     */
    Object pubIfAbsent(Object key, Object value);

    /**
     *
     */
    void clear();
}
