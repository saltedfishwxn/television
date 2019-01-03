package com.aaa.controller;

import com.aaa.entity.Tree;
import com.aaa.service.ResourceBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: 尚冠峰
 * @Date: 2018/12/15 0015 15:36
 * @Version 1.0
 */
@Controller
@RequestMapping("/power")
public class ResourcesController extends  BaseController {
    @Autowired
    private ResourceBiz resourceBizImpl;
    /**
     * 显示所有的权限，根据当前用户
     */
    @RequestMapping("/showAllResources")
    @ResponseBody
    public List<Tree> showAllResources(){
        //通过shiro安全框架获取当前登录名
        String shiroUserName = getShiroUserName();
        System.out.println("获取当前登录名"+shiroUserName);
        List<Tree> trees = resourceBizImpl.showAllResources(shiroUserName);
        System.out.println("查询后的菜单"+trees);
        return trees;
    }
}
