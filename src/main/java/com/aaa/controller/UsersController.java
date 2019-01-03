package com.aaa.controller;
/**
 * @ClassName ComplaintsController
 * @Author Lxl
 * @Date 2018/12/28 14:19
 **/
import com.aaa.entity.Users;
import com.aaa.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UsersController extends BaseController{

    @Autowired
    private UsersService service;

    @Value("${fileupload.path}")
    private String filePath;

    /**
     * 展示员工页面
     * @return
     */
    @RequestMapping("/showUser")
    public String showUser(){
        return "user";
    }

    /**
     * 前台下拉框 选择角色 信息
     * @return
     */
    @RequestMapping("/listRoles")
    @ResponseBody
    public Map<String,Object> listRoles(){
        return service.listRoles();
    }

    /**
     * 数据表格展示的员工信息
     * @param query
     * @return
     */
    @RequestMapping("/listUser")
    @ResponseBody
    public Map<String,Object> listUser(@RequestBody Map<String,Object> query){
        return service.listUser(query);
    }

    /**
     * 添加员工手机号时，判断手机号是否存在
     * @param query
     * @return
     */
    @RequestMapping("/phoneChange")
    @ResponseBody
    public Map<String,Object> phoneChange(@RequestBody Map<String,Object> query){
        return service.phoneChange(query);
    }

    /**
     * 添加员工
     * @param users
     * @return
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public Map<String,Object> addUser(@RequestBody Users users){
        return service.addUser(users);
    }

    /**
     * 编辑员工
     * @param users
     * @return
     */
    @RequestMapping("/editUser")
    @ResponseBody
    public Map<String,Object> editUser(@RequestBody Users users){
        return service.editUser(users);
    }

    /**
     * 批量导出员工信息
     * @param query
     * @return
     */
    @RequestMapping("/toExcelUsers")
    @ResponseBody
    public Map<String,Object> toExcelUsers(@RequestBody Map<String, List<Users>> query) throws Exception{
        return service.toExcelUsers(query);
    }

    /**
     * 批量导入员工信息
     * @param file
     * @return
     */
    @RequestMapping("/inExcelUsers")
    @ResponseBody
    public Map<String,Object> inExcelUsers(@RequestParam MultipartFile file) throws Exception {
        Map map2 = upload(file,filePath);
        String a = map2.get("url").toString();
        String path=filePath+a;
        return service.inExcelUsers(path);
    }

    /**
     * 头像上传
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public Map<Object, Object> upload(@RequestParam MultipartFile file) {
        Map<Object, Object> m = new HashMap<>();
        //上传到指定文件夹中
        Map map = upload(file, filePath);
        m.put("urla","/crm"+map.get("url"));
        return m;
    }



}
