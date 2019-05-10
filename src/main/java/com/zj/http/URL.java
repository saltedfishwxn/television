package com.zj.http;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 关于URLEncoder的编码和URLDecode解码
 * @author spring sky
 * My name:王晓楠
 *
 */
public class URL {
    public static void main(String[] args)throws Exception {
        /**
         * 编码
         *  * 如果字符a-z A-Z 0-9 或者_ ，他们不会被编码
         */
        String str1 = "abcdefghijklmnopqrstyvwxyz";
        String str1_1 = URLEncoder.encode(str1, "utf-8");
        System.out.println("str1_1="+str1_1);
        /**
         * 编码
         *  * 非字符a-z A-Z 0-9 或者_ ，他们被编码
         */
        String str2 = "[{\"agvId\":1}]";
        String str2_2 = URLEncoder.encode(str2, "utf-8");
        System.out.println("str2_2="+str2_2);
        System.out.println("----------------------------------");
        /**
         * 解码
         *
         */

        System.out.println(URLDecoder.decode(str1, "utf-8"));
        System.out.println(URLDecoder.decode(str2, "utf-8"));

        System.out.println("-----------------------------------");
        /**
         * 解码
         * 如果字符a-z A-Z 0-9 或者_，他们因为没有编译，所以不会被解密
         * 如果是%E4%B8%AD%E5%9B%BD%E4%BA%BA%E6%B0%91%E5%85%B1%E5%92%8C%E5%9B%BD，他们会被解码为文字
         */
        System.out.println(URLDecoder.decode(str1_1,"utf-8"));
        System.out.println(URLDecoder.decode(str2_2,"utf-8"));

        System.out.println("-----------------------------------");
        /**
         * 如果解密不是原本的字符类型(utf8-----gbk)
         */
        System.out.println(URLDecoder.decode(str1_1,"gbk"));
        System.out.println(URLDecoder.decode(str2_2,"gbk"));

        System.out.println("-----------------------------------");
        /**
         * 如果解密不是原本的字符类型(utf8-----ISO-8859-1)
         */
        System.out.println(URLDecoder.decode(str1_1,"ISO-8859-1"));
        System.out.println(URLDecoder.decode(str2_2,"ISO-8859-1"));
    }
}
