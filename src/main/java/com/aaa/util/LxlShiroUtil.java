package com.aaa.util;

import com.aaa.entity.Users;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * shiro框架相关
 * @Author: 尚冠峰
 * @Date: 2018/12/13 0013 10:27
 * @Version 1.0
 */
public class LxlShiroUtil {
    public static String md5(String text, String key) throws Exception {
        //加密后的字符串
        String encodeStr=DigestUtils.md5DigestAsHex(text.getBytes());
        System.out.println("MD5加密后的字符串为:encodeStr="+encodeStr);
        return encodeStr;
    }

    /**
     * 对用户的密码进行加盐加密,使用md5方式
     * @param users
     * @return
     */
    public static Users encryptPassword(Users users){
        //加密的次数
        int hashIterations = 1000;
        //盐值
        String salt = UUID.randomUUID().toString();
        //密码
        Object credentials = users.getPassword();
        //加密方式
        String hashAlgorithmName = "MD5";
        String enPassword = new SimpleHash(hashAlgorithmName, credentials,
                salt, hashIterations).toString();
        System.out.println("加密的盐值----->" + salt);
        System.out.println("加密后的值----->" + enPassword);
        users.setPassword(enPassword);
        users.setSalt(salt);
        return users;
    }

    public static void main(String[] args) throws Exception {
        int hashIterations = 1000;//加密的次数
        // String salt = UUID.randomUUID().toString();
         String salt = "test";
        Object credentials = "1";//密码
        String hashAlgorithmName = "MD5";//加密方式
        SimpleHash simpleHash = new SimpleHash(hashAlgorithmName, credentials, salt,
                 hashIterations);
        System.out.println("加密后的值----->" + simpleHash);
        System.out.println("加密的盐值----->" + salt);
       /* String fdsaf = md5("123456", "abc");
        System.out.println(fdsaf);*/
    }
}
