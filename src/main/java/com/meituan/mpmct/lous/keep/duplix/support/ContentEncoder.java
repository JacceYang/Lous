package com.meituan.mpmct.lous.keep.duplix.support;

/**
 *  Request content encoder. to compress the request content a encoder can
 *  be used to mark or compress the content. currently support MD5
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 10:25 AM 2019/8/27
 **/
public interface ContentEncoder {

    String encode(byte[] content);
}
