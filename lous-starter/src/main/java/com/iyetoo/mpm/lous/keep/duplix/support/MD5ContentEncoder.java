package com.iyetoo.mpm.lous.keep.duplix.support;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 10:26 AM 2019/8/27
 **/
public class MD5ContentEncoder implements ContentEncoder {

    @Override
    public String encode(byte[] content) {

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(content, 0, content.length);
            return new BigInteger(1, md5.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

//    public static String encode11(byte[] content) {
//
//        try {
//            MessageDigest md5 = MessageDigest.getInstance("MD5");
//            md5.update(content, 0, content.length);
//            return new BigInteger(1, md5.digest()).toString(16);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//
//
//    public static void main(String[] args) {
//        String var1=encode11(new String("阳差").getBytes());
//
//        String var2=encode11("阳差".getBytes());
//
//        System.out.println(var2);
//    }
}
