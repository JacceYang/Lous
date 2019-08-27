package com.meituan.mpmct.lous.keep.duplica.interceptor;

import com.meituan.mpmct.lous.keep.duplica.support.MemCache;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 10:10 PM 2019/8/24
 **/
public class DuplicaAspectSupport implements BeanFactoryAware {

    private DuplicaSource duplicaSource = new AnnotationDuplicaSource();

    private DuplicaInvokeContextParser invokeContextParser = new DuplicaInvokeContextParser();

    private MemCache memCache;

    private BeanFactory beanFactory;

    public Object execute(DuplicaInvoker invoker, Method method, Object targetObject, Object[] parameter) throws Throwable {

        // 1. 解析请求的元数据部分,确认请求来源
        DuplicaSourceContext duplicaSourceContext = duplicaSource.getDuplicaSourceContext(method, targetObject, parameter, beanFactory);

        // 2. 构建请求的实际上线文
        DuplicaInvokeContext invokeContext = invokeContextParser.parseInvokeContext(method, duplicaSourceContext);

        // 3. 检查请求是否
        DuplicaInvokeContainer invokeContainer = new DuplicaInvokeContainer(memCache, invokeContext, duplicaSourceContext);

        if (invokeContainer.runCheck()) {
            return invokeContainer.fastAck();
        }

        return invoker.invoke();
    }

    @Autowired
    public void setMemCache(Collection<MemCache> cache) {
        Assert.isTrue(cache != null && cache.size() < 2, "Only one memCache should be configured.");
        this.memCache = cache.iterator().next();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
