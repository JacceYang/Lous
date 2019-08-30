package com.iyetoo.mpm.lous.keep.power.support;

/**
 * @author JacceYang
 *  handler 处理的的中间状态记录类,SNAPSHOT
 * @since 1.0.0 Initialized in 9:07 PM 2019/8/19
 **/
public class RunStatus {

    private static int SUCCESS = 0x00000;

    private static int INFO = 0x00010;

    private static int WARNING = 0x00100;

    private static int ERROE = 0x01000;

    private static int[] STATUS = new int[]{INFO, WARNING, ERROE};

    private int currentStatus = SUCCESS;

    private String msg;

    private Throwable throwable;

    private String info;

    public void setMsg(String msg) {
        this.msg = msg;
        currentStatus = INFO;
    }

    public void setError(Throwable throwable) {
        this.throwable = throwable;
        currentStatus = ERROE;
    }

    public void setInfo(String info) {
        this.info = info;
        currentStatus = INFO;
    }

    int getCurrentStatus() {
        return currentStatus;
    }

}
