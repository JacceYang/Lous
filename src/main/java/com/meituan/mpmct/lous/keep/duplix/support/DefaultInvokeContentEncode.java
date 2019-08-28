package com.meituan.mpmct.lous.keep.duplix.support;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 8:52 PM 2019/8/25
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
