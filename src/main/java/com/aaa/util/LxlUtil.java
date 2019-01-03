package com.aaa.util;

import org.apache.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

public class LxlUtil {
    public static void main(String[] args) {
        sendMsg("13633932949","[易客]"+"感谢您宝贵的意见，我们会继续努力，做到更好，祝您生活愉快，万事如意！");
    }
    public static void sendMsg(String phone,String content) {
        String host = "http://dingxinyx.market.alicloudapi.com";
        String path = "/dx/marketSendSms";
        String method = "POST";
        String appcode = "0219883b6ce44691a29756da9ed3f664";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", phone);
        querys.put("param", "param");
        querys.put("tpl_id", "TP18041310");
        Map<String, String> bodys = new HashMap<String, String>();


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
