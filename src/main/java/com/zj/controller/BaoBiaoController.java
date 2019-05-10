package com.zj.controller;

import com.zj.entity.JsonBean;
import com.zj.service.BaoBiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 王晓楠
 * @Description
 * @create 2019-04-15 16:47
 */
@Controller
@RequestMapping("/baobiao")
public class BaoBiaoController {
    @Autowired
    private BaoBiaoService baobiaoServiceImpl;
    //用来定义一个保存输出的变量

    private JsonBean jb =new JsonBean();
    @RequestMapping("/test")
    @ResponseBody
    public JsonBean test(){
        return jb;
    }
    /**
     * 跳转到报表页
     * @return
     */
    @RequestMapping("/jump")
    public String baoBiaoTwo(){
        return "index";
    }
    @RequestMapping("/jumptwo")
    public String baoBiao(){
        return "404";
    }
    @RequestMapping("/post")
    @ResponseBody
    public JsonBean message(@RequestBody JsonBean json ){
        jb=json;
        return jb;
    }
    /**
     * 查询所有订单信息
     * @param
     * @return
     *
     *@RequestMapping("/list")
     *@ResponseBody
     *public Map listProduct(@RequestBody Map<String,Object> query){
     *    Map map=baobiaoServiceImpl.findAll(query);
     *   return map;
     *}
     */
}
