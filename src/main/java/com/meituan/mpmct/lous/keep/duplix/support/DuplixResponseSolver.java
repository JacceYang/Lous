package com.meituan.mpmct.lous.keep.duplix.support;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 8:02 PM 2019/8/25
 **/
public interface DuplixResponseSolver {

    /**
     * 拒绝处理
     *
     * @param <T>
     * @return
     */
    <T> T reject();

    /**
     * 继续处理
     */
    void pass();
}