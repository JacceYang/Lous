package com.meituan.mpmct.lous.cache.interceptor;

import com.meituan.mpmct.lous.cache.Cache;
import com.meituan.mpmct.lous.cache.CacheManager;
import com.meituan.mpmct.lous.cache.operation.CacheOperation;
import com.meituan.mpmct.lous.cache.operation.CacheOperationContext;
import com.meituan.mpmct.lous.cache.operation.CacheOperationSource;
import com.meituan.mpmct.lous.cache.support.CacheManagerSolver;
import com.meituan.mpmct.lous.cache.support.CacheManagerSolverSupport;
import com.meituan.mpmct.lous.cache.support.CacheSolver;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodClassKey;
import org.springframework.expression.Expression;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 3:11 PM 2019/8/11
 **/
public class CacheAspectSupport implements BeanFactoryAware{

    private CacheManagerSolver cacheManagerSolver;
    private CacheSolver cacheSolver;
    private CacheOperationSource cacheOperationSource;

    private CacheOperationExpressionEvaluator cacheOperationExpressionEvaluator;

    private BeanFactory beanFactory;

    public CacheOperationSource getCacheOperationSource() {
        return cacheOperationSource;
    }

    public void setCacheOperationSource(CacheOperationSource cacheOperationSource) {
        this.cacheOperationSource = cacheOperationSource;
    }

    protected Object execute(CacheOperationInvoker invoker, Object target, Method method, Object... args) {
        Class<?> targetClass = getTargetClass(target);
        CacheOperationSource cacheOperationSource = getCacheOperationSource();
        CacheOperation cacheOperation = cacheOperationSource.getCacheOperation(targetClass, method);
        CacheOperationContext cacheOperationContext = new CacheOperationContext(method, target, args, cacheOperation);
        Object execute = execute(method,cacheOperationContext);
        if (execute != null) {
            return execute;
        }

        Object invokeValue = invoker.invoke();

        storeCacheAfter(cacheOperationContext,cacheOperationContext.getValueKey(),invokeValue);

        return invokeValue;
    }


    protected void storeCacheAfter(CacheOperationContext operationContext,Object key,Object value){

        List<CacheManager> cacheManager = getCacheManagerSolver().getCacheManager(operationContext.getCachingModes());

        for (Cache cache : getCacheSolver().determineUltimateCache(cacheManager,operationContext.getCacheName())) {

            cache.pubIfAbsent(key,value);
        }

    }

    private Object execute(Method method,CacheOperationContext operationContext) {


        Expression expression = getCacheOperationExpressionEvaluator().getExpression(method,
                method.getDeclaringClass(), beanFactory, operationContext.getKey());

        Object valueKey = expression.getValue(new CacheEvaluationContext(this, operationContext.getTargetMethod(), operationContext.getParameters(), new DefaultParameterNameDiscoverer()));
        operationContext.setValueKey(valueKey);
        List<CacheManager> cacheManager = getCacheManagerSolver().getCacheManager(operationContext.getCachingModes());

        for (Cache cache : getCacheSolver().determineUltimateCache(cacheManager,operationContext.getCacheName())) {
            Object value = cache.getValue(valueKey);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    public CacheOperationExpressionEvaluator getCacheOperationExpressionEvaluator() {
        if (cacheOperationExpressionEvaluator==null){
            cacheOperationExpressionEvaluator=new CacheOperationExpressionEvaluator();
        }
        return cacheOperationExpressionEvaluator;
    }

    public void setCacheOperationExpressionEvaluator(CacheOperationExpressionEvaluator cacheOperationExpressionEvaluator) {
        this.cacheOperationExpressionEvaluator = cacheOperationExpressionEvaluator;
    }

    private Class<?> getTargetClass(Object target) {
        return AopProxyUtils.ultimateTargetClass(target);
    }

    public CacheManagerSolver getCacheManagerSolver() {
        return cacheManagerSolver;
    }

    public void setCacheManagerSolver(CacheManagerSolver cacheManagerSolver) {
        this.cacheManagerSolver = cacheManagerSolver;
    }

    public CacheSolver getCacheSolver() {
        return cacheSolver;
    }

    public void setCacheSolver(CacheSolver cacheSolver) {
        this.cacheSolver = cacheSolver;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory=beanFactory;
    }
}
