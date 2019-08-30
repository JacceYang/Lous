package com.iyetoo.mpm.lous.cache;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 5:37 PM 2019/8/11
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
