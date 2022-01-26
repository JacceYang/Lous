package com.iyetoo.mpm.lous.keep.duplix.support;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 8:02 PM 2019/8/25
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
