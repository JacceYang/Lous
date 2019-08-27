package com.meituan.mpmct.lous.keep.duplica.ext;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.util.RamUsageEstimator;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

/** {@link LocalMemCacheMonitor } is designed to constrict the memory to space (0,memMaxSiz]
 * if memMaxSiz is defined.
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 7:24 PM 2019/8/27
 **/
@SuppressWarnings("all")
public class LocalMemCacheMonitor {

    private static final Log logger = LogFactory.getLog(LocalMemCacheMonitor.class);
    /**
     * the max mem size of local cache.
     */
    private static long memMaxSiz = 0;

    /**
     * default step
     */
    private static int step = 100;

    private static int memUnit=1024;
    private static final Thread memControlThread;

    private static volatile Map<Object, LocalMemCache.WrappedValue> cacheSpace;

    static {
        if (memMaxSiz > 0) {

            memControlThread = new Thread("initialized memControlThread") {
                @Override
                public void run() {
                    for (; ; ) {
                        try {
                            this.currentThread().sleep(step);
                            clean();
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

    public LocalMemCacheMonitor(Map<Object, LocalMemCache.WrappedValue> cacheSpace,String maxSize,Integer step) {
        this.cacheSpace = cacheSpace;
        this.step=step;
        this.memMaxSiz=parseSize(maxSize);
    }

    private long parseSize(String maxSize){
        String size = maxSize.trim().toUpperCase();
        int m = size.lastIndexOf("M");
        int kb = size.lastIndexOf("KB");
        int b=size.lastIndexOf("B");
        String substring = size.substring(0, m);
        return Long.valueOf(substring).longValue()*memUnit;
    }

    static void clean() {
        long currentSize = RamUsageEstimator.sizeOf(cacheSpace);
        if (currentSize > memMaxSiz) {
            Optional<Map.Entry<Object, LocalMemCache.WrappedValue>> min = cacheSpace.entrySet().stream().min(new Comparator<Map.Entry<Object, LocalMemCache.WrappedValue>>() {
                @Override
                public int compare(Map.Entry<Object, LocalMemCache.WrappedValue> o1, Map.Entry<Object, LocalMemCache.WrappedValue> o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }
            });
            if (min.isPresent()){
                System.out.println("clean memery !!!"+min.get().getKey());
                cacheSpace.remove(min.get().getKey());
            }
        }
    }
}
