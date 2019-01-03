package com.aaa.controller;

import com.aaa.entity.ResultModel;
import com.aaa.query.FollowInfo;
import com.aaa.query.FollowQuery;
import com.aaa.service.CustomerFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * code is far away from bug with the animal protecting
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Description :客户跟进controller
 * ---------------------------------
 * @Author : An
 * @Date : Create in 2018/12/18 001811:24
 */
@Controller
@RequestMapping("/customerFollow")
public class CustomerFollowController extends BaseController {

    /**
     * 该类独用 进行类内封装
     * 作用 根据当前用户名 返回该员工的角色名称role-name,角色id role-role_id,员工id user-id
     * @return
     */
    private Map getLoginInfo(){
        //获取当前用户登录名
        String shiroUserName = getShiroUserName();
        //根据当前用户名查询该员工的角色名称role-name,角色id role-role_id,员工id user-id
        return customerFollowServiceImpl.checkEmpRoleName(shiroUserName);
    }

    /**
     * 返回待跟进的客户页面
     * @return
     */
    @RequestMapping("/toCustomerFollow")
    public String toCustomerFollow(){
        return "customerFollow";
    }

    /**
     * 测试返回待处理条数
     * @return
     */
        @RequestMapping("/toShowShareCustomer")
    public String toShowShareCustomer(){
        return "customerShare.html";
    }
    /**
     * 注入业务层
     */
    @Autowired
    CustomerFollowService customerFollowServiceImpl;

    /**
     * 查询客户信息 并将信息共享到客户跟进页面
     * @param customerId
     * @param model
     * @return
     */
    @RequestMapping("/followCustomer")
    public String followCustomer(Integer customerId, Model model){
        System.out.println(customerId);
        Map loginInfo = getLoginInfo();
        Integer id = (Integer) loginInfo.get("id");

        Map map = customerFollowServiceImpl.getFollowInfoByCustomerId(customerId,id);
        model.addAttribute("map",map);

        return "CustomerFellow";
    }

    /**
     * 查询未回访的客户信息
     * @return
     */
    @RequestMapping("/listNotVisited")
    @ResponseBody
    public Map listNotVisited(@RequestBody Map<String, Object> query){
        System.out.println(query.get("pageNo"));
        System.out.println(query.get("pageSize"));

        return customerFollowServiceImpl.listNotVisited(query);
    }

    /**
     * 使用实体类接收前台传递的信息
     *
     * @param
     * @return 通过条件筛选后的结果
     */
    @RequestMapping("/followQuery")
    @ResponseBody
    public Map listFollowQuery(@RequestBody FollowQuery followQuery) {
        System.out.println(followQuery);
        //获取员工身份信息
        Map map = getLoginInfo();

        return customerFollowServiceImpl.listFollowQuery(followQuery, map);

    }

    /**
     * 接收前台传递的客户跟进信息 将信息传递到service层 添加到数据库
     * @return 状态信息
     */
    @RequestMapping("/insertFollowInfo")
    @ResponseBody
    public ResultModel insertFollowInfo(@RequestBody FollowInfo followInfo){
        //System.out.println(followInfo.getUId());
        int rows = customerFollowServiceImpl.insertFollowInfo(followInfo);

        if (rows > 0) {
            return returnSuccess("添加成功");
        }
        return returnError("添加失败");
    }

    /**
     * 通过客户的ID获取历史跟进记录
     * @param customerId
     * @return
     */
    @RequestMapping("/getFollowHistory")
    @ResponseBody
    public List<Map> getFollowHistory(@RequestBody Map<String, Object> customerId){
        //System.out.println(empId);//Object
        int a = Integer.parseInt(customerId.get("customerId")+"") ;
        List<Map> history = customerFollowServiceImpl.getFollowHistory(a);
        System.out.println(history);
        return history;
    }

    /**
     * 查找所有销售员工信息 返回前台 为分享客户做准备
     * @return
     */
    @RequestMapping("/findUsers")
    @ResponseBody
    public List<Map> findUsers(){
        Map loginInfo = getLoginInfo();
        int id = (int) loginInfo.get("id");

        return  customerFollowServiceImpl.findUsers(id);
    }

    /**
     * 将需要分享的客户插入到user_customer中
     * @return
     */
    @RequestMapping("/insertShareCustomer")
    @ResponseBody
    public ResultModel insertShareCustomer(@RequestBody Map<String, Object> map){

        Map params = (Map) map.get("params");

        int cusId = (int) params.get("cusId");

        int shareId = (int) params.get("shareId");
        //查询被共享的客户是否已有该客户
        int isExist = customerFollowServiceImpl.customerIsExist(cusId, shareId);
        if (isExist < 1){
            int row = customerFollowServiceImpl.insertShareCustomer(params);
            if (row > 0) {
                return returnSuccess("分享成功");
            }else{
                return returnError("分享失败");
            }
        }
        return returnError("该员工已经有了这个客户,请不要重复分享!");
    }

    /**
     * 返回待处理的回访数量
     * @return
     */
    @RequestMapping("/getCountNeedFollow")
    @ResponseBody
    public int countNeedFollow(){
        //使用封装后的方法获取登录的信息
        Map map = getLoginInfo();
        //根据map获取应查到的待回访数量
        int count = customerFollowServiceImpl.getCountNeedFollow(map);
        return count;
    }

    /**
     * 获取共享给别人的客户
     * 获取别人共享给自己的客户
     * isShare非0客户
     * @return
     */
    @RequestMapping("/listShareCustomer")
    @ResponseBody
    public List<Map> listShareCustomer(){
        //将登陆信息传递到后台
        Map loginInfo = getLoginInfo();


        return customerFollowServiceImpl.listShareCustomer(loginInfo);
    }

    /**
     * 根据前台传递的客户id取消客户共享
     * @return
     */
    @RequestMapping("/cancelShareCustomer")
    @ResponseBody
    public ResultModel cancelShareCustomer(Integer cid ){
        System.out.println("传递到后台需要取消共享的客户id是:"+cid);
       int rows =  customerFollowServiceImpl.cancelShareCustomer(cid);
        System.out.println("返回的行数是:"+rows);
       if (rows > 0){
           return returnSuccess("取消共享客户成功!");
       }
       return returnError("取消共享客户失败!");
    }

    /**
     * 登录成功后的页面
     * @return
     */
    @RequestMapping("/loginSuccess")
    public String loginSuccess(){
        return "mainLoginSuccess";
    }

    /**
     * 查询客户基盘数量
     * @return
     */
    @RequestMapping("/listCustomerCountByLevel")
    @ResponseBody
    public List<Map> listCustomerCountByLevel(){
        Map loginInfo = getLoginInfo();
        int rid = (int) loginInfo.get("role_id");
        int uid = (int) loginInfo.get("id");

        return customerFollowServiceImpl.listCustomerCountByLevel(rid, uid);
    }
}
