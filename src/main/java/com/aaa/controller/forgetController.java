package com.aaa.controller;

import com.aaa.entity.ResultsModel;
import com.aaa.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Demo class
 * 忘记密码
 * @author 尚冠峰
 * @date 2018/12/25
 */
@Controller
@RequestMapping("/fg")
public class ForgetController extends BaseController {

    @Autowired
    private ILoginService loginService;
    /**
     * 忘记密码 跳转页面
     * @return
     */
    @RequestMapping("/forget")
    public String forget(){
        return "forgetPw";
    }

    /**
     * 验证手机号及获取验证码
     * @param map
     * @return
     */
    @RequestMapping("/forgetPw")
    @ResponseBody
    public ResultsModel forgetPw(@RequestBody Map map){
        ResultsModel result = loginService.forgetPw(map);
        return result;
    }

    /**
     * 跳转至用户填写验证码界面
     * @param map
     * @return
     */
    @RequestMapping("/getCode")
    @ResponseBody
    public String getCode(@RequestBody Map map){
        String code = map.get("code").toString();
        String checkCode = map.get("checkCode").toString();

        return "inputCode";
    }

    /**
     * 修改密码
     * @param map
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public ResultsModel update(@RequestBody Map map){
        int i= loginService.update(map);
        if(i==1){
            return returnSuccessInfo("修改成功,请登录");
        }else{
            return returnErrorInfo("修改失败，请重新修改");
        }
    }


}
