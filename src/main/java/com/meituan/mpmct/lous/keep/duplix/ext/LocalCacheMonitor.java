package com.meituan.mpmct.lous.keep.duplix.ext;


import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

/** {@link LocalCacheMonitor } is designed to constrict the memory to space (0,memMaxSiz]
 * if memMaxSiz is defined.
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 7:24 PM 2019/8/27
 **/
@SuppressWarnings("all")
public class LocalCacheMonitor {

    private static final Log logger = LogFactory.getLog(LocalCacheMonitor.class);
    /**
     * the max mem size of local cache.
     */
    private static long memMaxSiz = 0;

    /**
     * default step
     */
    private static int step = 100;
    /**
     * A deamon thread to eliminate cache with the less live time when memMaxSize is set.
     */
    private static  Thread memControlThread;

    /**
     * Monitored cache reference.
     */
    private static volatile Map<Object, LocalMemCache.WrappedValue> cacheSpace;

    static void intializ() {
        if (memMaxSiz > 0) {

            memControlThread = new Thread("initialized memControlThread") {
                @Override
                public void run() {
                    for (; ; ) {
                        try {
                            this.currentThread().sleep(step);
                            eliminate();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            memControlThread.setDaemon(true);
            memControlThread.setUncaughtExceptionHandler((t, e) -> {
                logger.warn("Exception has been raised by");
            });

            memControlThread.start();
        } else {
            memControlThread = null;
        }
    }

    public LocalCacheMonitor(Map<Object, LocalMemCache.WrappedValue> cacheSpace, String maxSize, Integer step) {
        this.cacheSpace = cacheSpace;
        this.step=step;
        this.memMaxSiz=parseSize(maxSize);
        intializ();
    }

    private long parseSize(String maxSize){
        String size = maxSize.trim().toUpperCase();
        int pos = findFirstApha(maxSize.getBytes());
        String sizeValue = size.substring(0, pos);
        String sizeUnit=size.substring(pos, maxSize.length());
        MemUnit unit = MemUnit.getUnit(sizeUnit);
        return unit.toByte(Long.valueOf(sizeValue));
    }

    private int findFirstApha(byte[] str){
        for (int idx=0;idx<str.length;idx++){
            if (str[idx]<'0' || str[idx]>'9'){
                return idx;
            }
        }
        return str.length;
    }

    static void eliminate() {
        long currentSize = ObjectSizeCalculator.getObjectSize(cacheSpace);
        if (currentSize > memMaxSiz) {
            Optional<Map.Entry<Object, LocalMemCache.WrappedValue>> min = cacheSpace.entrySet().stream().min(new Comparator<Map.Entry<Object, LocalMemCache.WrappedValue>>() {
                @Override
                public int compare(Map.Entry<Object, LocalMemCache.WrappedValue> o1, Map.Entry<Object, LocalMemCache.WrappedValue> o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }
            });
            if (min.isPresent()){
                cacheSpace.remove(min.get().getKey());
            }
        }
    }
}
