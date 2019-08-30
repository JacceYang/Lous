package com.iyetoo.mpm.lous.cache.operation;

import org.springframework.aop.support.AopUtils;
import org.springframework.core.MethodClassKey;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 12:53 PM 2019/8/11
 **/
public class AnnotationCacheOperationSource implements CacheOperationSource {


    private Map<MethodClassKey, CacheOperation> keyCacheOperation = new HashMap<>(200);

    /**
     * this cache operation is different with null ,it present a cached methodclass key operation
     */
    private CacheOperation NO_OPERATION = new CacheOperation();

    private AnnotationCacheOperationParser cacheOperationParser;

    public AnnotationCacheOperationSource() {
    }

    public AnnotationCacheOperationSource(AnnotationCacheOperationParser cacheOperationParser) {
        this.cacheOperationParser = cacheOperationParser;
    }

    @Override
    public CacheOperation getCacheOperation(Class<?> target, Method method) {

        if (method.getDeclaringClass() == Object.class) {
            return null;
        }

        MethodClassKey methodClassKey = generateOperationKey(target, method);

        // havn't been cache before
        if (keyCacheOperation.get(methodClassKey) == null) {
            CacheOperation cacheOperation = computeCacheOperation(target, method);
            cacheOperation = cacheOperation == null ? NO_OPERATION : cacheOperation;
            keyCacheOperation.put(methodClassKey, cacheOperation);
        }

        return keyCacheOperation.get(methodClassKey) == NO_OPERATION ? null : keyCacheOperation.get(methodClassKey);
    }


    private MethodClassKey generateOperationKey(Class<?> target, Method method) {
        return new MethodClassKey(method, target);
    }


    private CacheOperation computeCacheOperation(Class<?> target, Method method) {
        Method mostSpecificMethod = AopUtils.getMostSpecificMethod(method, target);

        return cacheOperationParser.parseCacheOperation(mostSpecificMethod);
    }

}
