package com.meituan.mpmct.lous.keep.duplica.support;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 10:26 AM 2019/8/27
 **/
public class MD5ContentEncoder implements ContentEncoder{

    @Override
    public String encode(byte[] content) {

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(content,0,content.length);
            return new BigInteger(1, md5.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
