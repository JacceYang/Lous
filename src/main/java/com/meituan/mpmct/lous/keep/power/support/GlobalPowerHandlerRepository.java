package com.meituan.mpmct.lous.keep.power.support;

import com.meituan.mpmct.lous.keep.annotation.Power;
import com.meituan.mpmct.lous.keep.event.ObservableEventCenter;
import com.meituan.mpmct.lous.keep.power.interceptor.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 5:12 PM 2019/8/19
 **/

public class GlobalPowerHandlerRepository implements BeanFactoryAware, GlobalPowerHandler, Observer {
    private final Log logger = LogFactory.getLog(getClass());

    private Map<String, PrePowerHandler> prePowerHandlerRepositry;

    private Map<String, PostPowerHandler> postPowerHandlerRepositry;

    private Map<String, PowerErrorHandler> powerErrorHandlerRepository;

    private Map<String, PowerChainHandler> powerChainHandlerRepositry;

    private Map<String, PowerInvokeCollector> powerInvokeCollectorReposity;

    private BeanFactory beanFactory;

    /**
     * you will not expose the parser to the up lever of a design princeple:
     * do not exposer too more information to outer user ,keep simple and keep
     * secret.
     */
    private PowerInvokeCollectorParser powerInvokeCollectorParser = new PowerInvokeCollectorParser();

    private List<PrePowerHandler> getPrePowerHandler(String[] preHandler) {

        LinkedHashSet<String> set = new LinkedHashSet<>(preHandler.length);
        Collections.addAll(set, preHandler);
        return getPrePowerHandler(set);
    }

    @Override
    public List<PrePowerHandler> getPrePowerHandler(Set<String> handlers) {

        if (prePowerHandlerRepositry == null || handlers == null) {
            return Collections.EMPTY_LIST;
        }

        List<PrePowerHandler> prePowerHandlerResult = new ArrayList<>();
        for (String handler : handlers) {
            PrePowerHandler abstractPrePowerHandler = prePowerHandlerRepositry.get(handler);
            if (abstractPrePowerHandler != null) {
                prePowerHandlerResult.add(abstractPrePowerHandler);
            }
        }

        return prePowerHandlerResult;
    }

    private List<PostPowerHandler> getPostPowerHandler(String[] handlers) {
        LinkedHashSet<String> set = new LinkedHashSet<>(handlers.length);
        Collections.addAll(set, handlers);
        return getPostPowerHandler(set);
    }

    @Override
    public List<PostPowerHandler> getPostPowerHandler(Set<String> handlers) {

        if (postPowerHandlerRepositry == null || handlers == null) {
            return Collections.EMPTY_LIST;
        }

        List<PostPowerHandler> postPowerHandlers = new ArrayList<>();
        for (String handler : handlers) {
            PostPowerHandler postPowerHandler = postPowerHandlerRepositry.get(handler);
            if (postPowerHandler != null) {
                postPowerHandlers.add(postPowerHandler);
            }
        }

        return postPowerHandlers;
    }

    @Override
    @Nullable
    public PowerErrorHandler getErrorHandler(String errorHandler) {
        if (powerErrorHandlerRepository == null || !StringUtils.hasText(errorHandler)) {
            return null;
        }
        return powerErrorHandlerRepository.get(errorHandler);
    }

    private List<PowerChainHandler> getPowerChainHandler(String[] handlers) {
        LinkedHashSet<String> set = new LinkedHashSet<>(handlers.length);
        Collections.addAll(set, handlers);
        return getPowerChainHandler(set);
    }

    @Override
    public List<PowerChainHandler> getPowerChainHandler(Set<String> handlers) {
        if (powerChainHandlerRepositry == null || handlers == null) {
            return Collections.EMPTY_LIST;
        }

        List<PowerChainHandler> powerChainHandlers = new ArrayList<>();
        for (String handler : handlers) {
            PowerChainHandler powerChainHandler = powerChainHandlerRepositry.get(handler);
            if (powerChainHandler != null) {
                powerChainHandlers.add(powerChainHandler);
            }
        }
        return powerChainHandlers;
    }

    @Override
    @Nullable
    public PowerInvokeCollector getPowerInvokeCollector(PowerInvokeCollectorContext collector) {
        // To do for performance.
        return powerInvokeCollectorParser.parseInvokeCollector(collector, beanFactory);
    }

     private void registryPreHandlers(Map<String, PrePowerHandler> preHandlers) {
        if (CollectionUtils.isEmpty(preHandlers)) {
            return;
        }
        prePowerHandlerRepositry = new HashMap<>(8);
        for (Map.Entry<String, PrePowerHandler> preHandler : preHandlers.entrySet()) {
            String handlerName =  preHandler.getValue().getName();
            if (!StringUtils.hasText(handlerName)){
                handlerName=preHandler.getKey();
            }
            prePowerHandlerRepositry.put(handlerName, preHandler.getValue());
        }
    }

    private void registryPostHandlers(@Nullable Map<String,PostPowerHandler> postHandlers) {
        if (CollectionUtils.isEmpty(postHandlers)) {
            return;
        }
        postPowerHandlerRepositry = new HashMap<>(8);
        for (Map.Entry<String,PostPowerHandler> postHandler : postHandlers.entrySet()) {
            String handlerName=postHandler.getValue().getName();
            if (!StringUtils.hasText(handlerName)){
                handlerName=postHandler.getKey();
            }
            postPowerHandlerRepositry.put(handlerName, postHandler.getValue());
        }
    }

    void registryErrorHandler(Map<String,PowerErrorHandler>  powerErrorHandlers) {

        if (CollectionUtils.isEmpty(powerErrorHandlers)) {
            return;
        }
        powerErrorHandlerRepository = new HashMap<>(8);
        for (Map.Entry<String,PowerErrorHandler> errorHandler : powerErrorHandlers.entrySet()) {
            String handlerName = errorHandler.getValue().getName();
            if (!StringUtils.hasText(handlerName)){
                handlerName= errorHandler.getKey();
            }
            powerErrorHandlerRepository.put(handlerName, errorHandler.getValue());
        }

    }

    void registryChain(Map<String,PowerChainHandler> powerChainHandlers) {
        if (CollectionUtils.isEmpty(powerChainHandlers)) {
            return;
        }
        powerChainHandlerRepositry = new HashMap<>(8);
        for (Map.Entry<String,PowerChainHandler> chainHandler : powerChainHandlers.entrySet()) {
            String handlerName = StringUtils.hasText(chainHandler.getValue().getName()) ? chainHandler.getValue().getName() : chainHandler.getKey();
            powerChainHandlerRepositry.put(handlerName, chainHandler.getValue());
        }
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

        this.beanFactory = beanFactory;
        ObservableEventCenter.addListener(this);
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            initialize(listableBeanFactory);
        }
    }

    private void initialize(ListableBeanFactory listableBeanFactory) {

        Map<String, PrePowerHandler> preHandlers = listableBeanFactory.getBeansOfType(PrePowerHandler.class);
        registryPreHandlers(preHandlers);

        Map<String, PostPowerHandler> postHandlers = listableBeanFactory.getBeansOfType(PostPowerHandler.class);
        registryPostHandlers(postHandlers);

        Map<String, PowerErrorHandler> powerErrorHandlers = listableBeanFactory.getBeansOfType(PowerErrorHandler.class);
        registryErrorHandler(powerErrorHandlers);

        Map<String, PowerChainHandler> powerChainHandlers = listableBeanFactory.getBeansOfType(PowerChainHandler.class);
        registryChain(powerChainHandlers);
    }

    @Override
    public void update(Observable o, Object arg) {

        if (arg instanceof Power) {
            String[] preHandlers = ((Power) arg).preHandler();
            if (preHandlers != null) {
                configValidate(preHandlers, Null -> this.getPrePowerHandler(preHandlers));
            }

            String[] postHandlers = ((Power) arg).postHandler();
            if (postHandlers != null) {
                configValidate(postHandlers, Null -> this.getPostPowerHandler(postHandlers));
            }

            String errorHandler = ((Power) arg).errorHandler();
            if (StringUtils.hasText(errorHandler) && getErrorHandler(errorHandler) == null) {
                throw new IllegalArgumentException("configuration error for errorHandler！！！");
            }

            String[] chain = ((Power) arg).chain();
            if (chain != null) {
                configValidate(chain, Null -> this.getPowerChainHandler(chain));
            }
        }
    }

    private void configValidate(String[] candidates, Function invoke) {
        Object candidateHandler = invoke.apply(null);
        if (candidateHandler instanceof Collection) {
            Collection<?> handlers = (Collection<?>) candidateHandler;
            if (handlers.size() == candidates.length) {
                return;
            }
        }
        throw new IllegalArgumentException("can not find all handlers for " + candidates.toString() + "!!!");
    }


}
