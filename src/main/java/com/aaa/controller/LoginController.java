package com.aaa.controller;

import com.aaa.entity.Resource;
import com.aaa.entity.ResultsModel;
import com.aaa.entity.UpdatePw;
import com.aaa.entity.User;
import com.aaa.service.ILoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Author: 尚冠峰
 * @Date: 2018/12/12 0012 22:25
 * @Version 1.0
 */
@Controller
@RequestMapping("/log")
public class LoginController extends  BaseController {
    @Autowired
    private ILoginService loginService;

    //退出的时候是get请求，主要是用于退出
    @RequestMapping("/getLogin")
    @ResponseBody
    public String getLogin(){
        String shiroUserName =getShiroUserName();
        String login = loginService.getLogin(shiroUserName);
        return login;
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }
    //post登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ResultsModel login(@RequestBody User user){
        System.out.println(user.getLoginName());
        System.out.println(user.getPassword());
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        try {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getLoginName(),user.getPassword());
        //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
            //return "index";
            return returnSuccessInfo("登录成功！");
        } catch (UnknownAccountException e) {
            System.out.println("账号不存在");
           // throw new RuntimeException("账号不存在！", e);
            return returnErrorInfo("账号不存在！");
        }catch(NullPointerException e){
            System.out.println("账号不存在");
            return returnErrorInfo("账号不存在！");
        } catch (DisabledAccountException e) {
            System.out.println("账号未启用");
            //throw new RuntimeException("账号未启用！", e);
            return returnErrorInfo("账号未启用！");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误");
            //throw new RuntimeException("密码错误！", e);
            return returnErrorInfo("密码错误！");
        } catch (Throwable e) {
            //throw new RuntimeException(e.getMessage(), e);
            return  returnErrorInfo(e.getMessage());
        }

    }

    @RequestMapping(value = "/toindex")
    public String toindex(){
        return "index.html";
    }
    //登出
    @RequestMapping(value = "/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login.html";
    }

    /**
     * 修改密码
     * @param pw
     * @return
     */
    @RequestMapping(value = "/updatePw")
    @ResponseBody
    public ResultsModel updatePw(@RequestBody UpdatePw pw){
        String shiroUserName =getShiroUserName();
        pw.setLoginname(shiroUserName);
        int i = loginService.updatePw(pw);
        if(i==1){
            return returnSuccessInfo("更改成功,请重新登录");
        }else{
            return returnErrorInfo("原始密码错误");
        }
    }





    //错误页面展示
    @RequestMapping(value = "/error",method = RequestMethod.POST)
    public String error(){
        return "error ok!";
    }

    //数据初始化
    @RequestMapping(value = "/addUser")
    public String addUser(@RequestBody User map){
        User user = loginService.addUser(map);
        return "addUser is ok! \n" + user;
    }

    //角色初始化
    /*@RequestMapping(value = "/addRole")
    public String addRole(@RequestBody Map<String,Object> map){
        Role role = loginService.addRole(map);
        return "addRole is ok! \n" + role;
    }*/

    //注解的使用
    @RequiresRoles("admin")
    //@RequiresPermissions("create")
    @RequestMapping(value = "/create")
    public String create(){
        return "Create success!";
    }
    @RequestMapping("/tojuese")
    public String tojuese(){
        return "juese";
    }
    @RequestMapping("/toziyuan")
    public String toziyuan(){
        return "ziyuan";
    }
    /**
     * 获取所有权限
     */
    @RequestMapping("/getPower")
    @ResponseBody
    public List<Resource> getPower(){

        List<Resource> list =  loginService.getPower();
        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i));
        }
        return list;
    }

    /**
     * 获取用火所拥有的权限
     * @return
     */
    @RequestMapping("/getPowerByUsername")
    @ResponseBody
    public List<Map> getPowerByUsername(@RequestBody Map map){

        int id = Integer.parseInt(map.get("id").toString());
        return loginService.getPowerByUsername(id);
    }

    /**
     * 获取用户修改后的权限
     * @param map
     * @return
     */
    @RequestMapping("/submitPower")
    @ResponseBody
    public String submitPower(@RequestBody  Map map){
        List list = (List) map.get("id");
        int roleId = (int) map.get("role");
        System.out.println(roleId);
        loginService.submitPower(list,roleId);
        return "aaa";
    }

    /**
     * 跳转至权限管理网页
     * @return
     */
    @RequestMapping("/power")
    public String power(){

        return "Power";
    }
    /**
     * 获取角色
     */
    @RequestMapping("/getRole")
    @ResponseBody
    public List<Map>  getRole(){
       return loginService.power();
    }

}
