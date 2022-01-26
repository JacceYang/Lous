package com.iyetoo.mpm.lous.keep.duplix.support;

/**
 *  Request content encoder. to compress the request content a encoder can
 *  be used to mark or compress the content. currently support MD5
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 10:25 AM 2019/8/27
 **/
public interface ContentEncoder {

    String encode(byte[] content);
}
