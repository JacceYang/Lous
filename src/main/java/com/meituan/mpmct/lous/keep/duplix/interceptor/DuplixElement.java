package com.meituan.mpmct.lous.keep.duplix.interceptor;

import com.meituan.mpmct.lous.keep.annotation.Scene;

import java.util.concurrent.TimeUnit;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 8:45 AM 2019/8/25
 **/
public class DuplixElement {

    private Scene scene;

    private String key;

    private TimeUnit unit;

    private int expire;

    private int times;

    private String msg;

    public DuplixElement(Builder builder) {
        this.scene = builder.getScene();
        this.key = builder.getKey();
        this.times = builder.getTimes();
        this.expire = builder.getExpire();
        this.unit = builder.getUnit();
        this.msg = builder.getMsg();
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public void setUnit(TimeUnit unit) {
        this.unit = unit;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class Builder {

        private Scene scene;

        private String key;

        private TimeUnit unit;

        private int expire;

        private int times;

        private String msg;

        public Scene getScene() {
            return scene;
        }

        public void setScene(Scene scene) {
            this.scene = scene;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public TimeUnit getUnit() {
            return unit;
        }

        public void setUnit(TimeUnit unit) {
            this.unit = unit;
        }

        public int getExpire() {
            return expire;
        }

        public void setExpire(int expire) {
            this.expire = expire;
        }

        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public DuplixElement build() {
            return new DuplixElement(this);
        }


    }
}
