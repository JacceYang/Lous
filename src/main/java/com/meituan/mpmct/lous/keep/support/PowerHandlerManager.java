package com.meituan.mpmct.lous.keep.support;

import org.springframework.core.MethodClassKey;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 4:40 PM 2019/8/19
 **/

public class PowerHandlerManager {

    private Map<MethodClassKey, PowerSourceContext> preHandlers = new HashMap<>(16);

    private Map<MethodClassKey, PowerSourceContext> postHandlers = new HashMap<>(16);

    public Map<MethodClassKey, PowerSourceContext> getPreHandlers() {
        return preHandlers;
    }

    public void setPreHandlers(Map<MethodClassKey, PowerSourceContext> preHandlers) {
        this.preHandlers = preHandlers;
    }

    public Map<MethodClassKey, PowerSourceContext> getPostHandlers() {
        return postHandlers;
    }

    public void setPostHandlers(Map<MethodClassKey, PowerSourceContext> postHandlers) {
        this.postHandlers = postHandlers;
    }
}
