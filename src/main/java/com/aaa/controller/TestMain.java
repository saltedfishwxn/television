package com.aaa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName TestMain
 * @Description TODO
 * @Author LP
 * @Date 2018/12/14 14:19
 * @Version 1.0
 **/
@Controller
@RequestMapping("/test")
public class TestMain {
    @RequestMapping("/jump")
    public String jump(){
        return "main";
    }
}
