package com.meituan.mpmct.lous.keep.interceptor;

import java.util.LinkedHashSet;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 6:22 PM 2019/8/19
 * @See @Power define
 **/
public class PowerElement {

    /**
     *  duplication preHandler doesn't permmit.
     */
    private LinkedHashSet<String> preHandlers;

    /**
     *
     */
    private LinkedHashSet<String> postHandlers;

    /**
     *
     */
    private LinkedHashSet<String> chains;

    /**
     *
     */
    private String errorHandler;


    public LinkedHashSet<String> getPreHandlers() {
        return preHandlers;
    }

    public LinkedHashSet<String> getPostHandlers() {
        return postHandlers;
    }

    public LinkedHashSet<String> getChains() {
        return chains;
    }

    public String getErrorHandler() {
        return errorHandler;
    }

    public PowerElement(Builder builder) {
        this.preHandlers = builder.preHandlers;
        this.postHandlers = builder.postHandlers;
        this.chains = builder.chains;
        this.errorHandler = builder.errorHandler;
    }

    public static class Builder {

        private LinkedHashSet<String> preHandlers;

        private LinkedHashSet<String> postHandlers;

        private LinkedHashSet<String> chains;

        private String errorHandler;

        public void setPreHandlers(String... preHandlers) {
            if (preHandlers != null) {
                this.preHandlers = new LinkedHashSet<>();
                for (String handler : preHandlers) {
                    this.preHandlers.add(handler);
                }
            }
        }

        public void setPostHandlers(String... postHandlers) {
            if (postHandlers != null) {
                this.postHandlers = new LinkedHashSet<>();
                for (String handler : postHandlers) {
                    this.postHandlers.add(handler);
                }
            }
        }

        public void setChains(String... chains) {
            if (chains != null) {
                this.chains = new LinkedHashSet<>();
                for (String chain : chains) {
                    this.chains.add(chain);
                }
            }
        }

        public void setErrorHandler(String errorHandler) {
            this.errorHandler = errorHandler;
        }

        public PowerElement build() {
            return new PowerElement(this);
        }
    }

}
