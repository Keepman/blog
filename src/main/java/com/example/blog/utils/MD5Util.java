package com.example.blog.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: zoulei
 * @Date: 2019/8/6 10:15
 * @Version 1.0
 */
public class MD5Util {
    public static String md5Encrpt(String value) {
        StringBuilder sb=new StringBuilder();
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(value.getBytes());
            byte[] target=md5.digest();
            for (int i=0;i<target.length;i++){
                int x=(int) target[i]&0xff;
                //加盐
                x=x+1;
                if(x<16){
                    sb.append(0);
                }else {
                    sb.append(Integer.toHexString(x));
                }

            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        String id = md5Encrpt("root");
        String id1 = md5Encrpt("admin");
        System.out.println(id);
        System.out.println(id1);
    }
}
