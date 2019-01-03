package com.aaa.controller;

import com.aaa.entity.Role;
import com.aaa.service.ILoginService;
import com.aaa.service.LRoleService;
import com.aaa.service.ResourceBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/lRole")
public class LRoleController extends BaseController{

    @Autowired
    private LRoleService service;

    @Autowired
    private ResourceBiz resourceBizImpl;

    @Autowired
    private ILoginService loginService;

    /**
     * 展示角色页面
     * @return
     */
    @RequestMapping("/showLRole")
    public String showLRole(){
        return "lRole";
    }

    /**
     * 前台数据表格展示信息
     * @return
     */
    @RequestMapping("/findAllRole")
    @ResponseBody
    public Map<String,Object> findAllRole(@RequestBody Map<String,Object> query){
        return service.findAllRole(query);
    }

    /**
     * 删除角色
     * @return
     */
    @RequestMapping("/deleteLRoleRoleById")
    @ResponseBody
    public Map<String,Object> deleteLRoleRoleById(Integer id){
        return service.delLRoleById(id);
    }

    /**
     * 新增角色
     * @return
     */
    @RequestMapping("/addLRole")
    @ResponseBody
    public Map<String,Object> addLRole(@RequestBody Role role){
        return service.addLRole(role);
    }

    /**
     * 编辑角色 启用 禁用在这里
     * @return
     */
    @RequestMapping("/editLRoleUser")
    @ResponseBody
    public Map<String,Object> editLRoleUser(@RequestBody Role role){
        return service.editLRoleUser(role);
    }

}
