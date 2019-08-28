package com.meituan.mpmct.lous.keep.duplix.support;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 10:07 PM 2019/8/25
 **/
public interface MemCache {

    /**
     * @param key   Key 数据
     * @param value 保存的内容
     * @param ms    时间毫秒
     */
    void putCache(Object key, String value, long ms,int times);

    /**
     *  Get the content of the key.
     * @param key
     * @return
     */
    String getCache(Object key);

}
