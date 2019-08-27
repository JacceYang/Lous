package com.meituan.mpmct.lous.demo;

import java.io.Serializable;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 6:10 PM 2019/8/27
 **/
public class ResponVo implements Serializable{

    private String msg;

    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ResponVo(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public ResponVo() {
    }

    public static ResponVo success(String data){

        return new ResponVo( data,200);
    }
}
