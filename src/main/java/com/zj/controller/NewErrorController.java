package com.zj.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
/**
 * @author 王晓楠
 * @Description
 * @create 2019-05-09 14:28
 */
@Controller
public class NewErrorController implements ErrorController {
        private int errorCode1=401;
        private int errorCode2=404;
        private int errorCode3=403;
        @RequestMapping("/error")
        public String handleError(HttpServletRequest request){
            //获取statusCode:401,404,500
            Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
            if(statusCode == errorCode1){
                return "401";
            }else if(statusCode == errorCode2){
                return "404";
            }else if(statusCode == errorCode3){
                return "403";
            }else{
                return "500";
            }

        }
        @Override
        public String getErrorPath() {
            return "error";
        }
}
