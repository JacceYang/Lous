package com.iyetoo.mpm.lous.keep.duplix.support;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 8:52 PM 2019/8/25
 **/
public class DefaultInvokeContentEncode implements InvokeContentEncode {

    @Override
    public String encode(String content) {
        // 内容过短,不用加密
        if (content.length() < 32) {
            return content;
        } else {
            ContentEncoder encoder = new MD5ContentEncoder();
            return encoder.encode(content.getBytes());
        }
    }
}
