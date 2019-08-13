package com.meituan.mpmct.lous.cache.operation;

import com.meituan.mpmct.lous.cache.annotation.GetCache;
import com.meituan.mpmct.lous.cache.annotation.PutCache;
import com.meituan.mpmct.lous.cache.config.AbstractCachingConfig;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 1:08 PM 2019/8/11
 **/
public class AnnotationCacheOperationParser {

    private AbstractCachingConfig cachingConfig;


    public CacheOperation parseCacheOperation(Class<?> target) {
        throw new UnsupportedOperationException("we do not support currently!");
    }

    public CacheOperation parseCacheOperation(Method method) {
        Collection<CacheOperation> ops = new ArrayList<>();

        Set<GetCache> allCetCache = AnnotatedElementUtils.findAllMergedAnnotations(method, GetCache.class);
        allCetCache.stream().forEach(getCache -> {
            CacheOperation cacheOperation = parseGetCacheAnnotation(getCache);
            ops.add(cacheOperation);
        });

        Set<PutCache> allPutCache = AnnotatedElementUtils.findAllMergedAnnotations(method, PutCache.class);

        allPutCache.stream().forEach(putCache -> {
            CacheOperation cacheOperation = parsePutCacheAnnotation(putCache);
            ops.add(cacheOperation);

        });

        if (!ops.isEmpty() && ops.size() > 1) {
            throw new UnsupportedOperationException("mutiple Cache annotation founded!");
        }

        if (!ops.isEmpty()) {
            CacheOperation next = ops.iterator().next();
            setDefaultConfig(next, cachingConfig);
            return next;
        }
        return null;
    }

    private CacheOperation setDefaultConfig(CacheOperation cacheOperation, AbstractCachingConfig config) {

        return cacheOperation;
    }

    private CacheOperation parseGetCacheAnnotation(GetCache getCache) {
        GetCacheOperation.Builder builder = new GetCacheOperation.Builder();

        builder.setCacheNames(getCache.cacheName());
        builder.setCachingModes(getCache.cacheMode());
        builder.setKeyGenerator(getCache.keyGenerator());
        builder.setKey(getCache.key());

        return builder.build();
    }

    private CacheOperation parsePutCacheAnnotation(PutCache putCache) {
        PutCacheOperation.Builder builder = new PutCacheOperation.Builder();

        builder.setCacheNames(putCache.cacheName());
        builder.setCachingModes(putCache.cacheMode());
        builder.setKey(putCache.key());
        builder.setKeyGenerator(putCache.keyGenerator());
        builder.setAsync(putCache.async());

        return builder.build();

    }
}
