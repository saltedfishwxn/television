package com.aaa.controller;

import com.aaa.entity.*;
import com.aaa.service.OrderBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Demo class
 * 合同
 * @author 尚冠峰
 * @date 2018/12/19
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController{
    @Autowired
    private OrderBiz order;
    @Value("${fileupload.path}")
    private String filePath;

    /**
     * 合同订单的填写
     * alm 1.2号修改
     */
    @RequestMapping("/addOrder")
    public String  addOrder(int customerId, Model model){
        //int i=5;
        Customer ct=order.getOrder(customerId);
        String shiroUserName = getShiroUserName();
        String name=order.getName(shiroUserName);
        ct.setComment(name);
        model.addAttribute("list",ct);
        return "order.html";
    }
    /**
     * 存储合同信息
     *
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResultsModel addOrder(@RequestBody Order od) throws Exception {
        od.setId(5);
        //获取order的属性
        Field[] field = od.getClass().getDeclaredFields();
        //遍历属性
        for(int j=0 ; j<field.length ; j++){
            String name = field[j].getName();
            System.out.println(name);
            //拼接属性字符串
            name = name.substring(0,1).toUpperCase()+name.substring(1);
            //获取给属性的get方法
            Method m = od.getClass().getMethod("get"+name);
            Object value=null;
            try{
                //获取属性值
            value =m.invoke(od);
            }catch(Exception e){
                return returnErrorInfo("请完善信息");
            }
            if(value==null || od.getAllprice()==0.0 || value.equals("")){
                return returnErrorInfo("请完善信息");
            }
        }
        order.addOrder(od);
        return returnSuccessInfo("创建成功");

    }

    /**
     * 跳转至合同展示界面
     * @return
     */
    @RequestMapping("/view")
    public String  view(){
        return "orderview.html";
    }

    @RequestMapping("/upload")
    @ResponseBody
    public Map  upload(@RequestParam MultipartFile file) {
        return upload(file,filePath);
    }
    /**
     * 展示全部合同信息
     * @param
     * @return
     */
    @RequestMapping("/allView")
    @ResponseBody
    public List<Map> allView(@RequestBody Map<String,Object> map){
        Object pageSize = map.get("pageSize");
        Object pageNo = map.get("pageNo");
        int pn = Integer.parseInt(String.valueOf(pageNo));
        int ps =Integer.parseInt(String.valueOf(pageSize));
        int begin=(pn-1)*ps;
        map.put("begin",begin);
        String shiroUserName = getShiroUserName();
        int id = order.getidByName(shiroUserName);

        List<Map> list=null;
        if(id==4){
            list = order.allView(map);
        }else{
            System.out.println(map);
            String name = order.getName(shiroUserName);
            map.put("sname",name);
           list=order.allViewByName(map);
        }
        Map<Object, Object> map2 = new HashMap<>();
        map2.put("mid",id);
      //  list.add(map2);

        return list;
    }
    /**
     * 合同的总条数
     */
    @RequestMapping("/getTotel")
    @ResponseBody
    public int getTotel(@RequestBody Map map){
        String shiroUserName = getShiroUserName();
        int id = order.getidByName(shiroUserName);
        int totel=0;
        if(id==4){
            totel = order.getTotel(map);
        }else{

            String name = order.getName(shiroUserName);
            map.put("sname",name);
            totel = order.getTotelByName(map);
        }
        return totel;
    }
    /**
     * 搜索合同信息的展示
     */
    @RequestMapping("/orderView")
    @ResponseBody
    public List<Map> orderView(@RequestBody OrderSearch search) throws ParseException {
        String shiroUserName = getShiroUserName();
        int id = order.getidByName(shiroUserName);
        List<Map> list = null;
        if(id==4){
            list = order.orderView(search);
        }else{
            String name = order.getName(shiroUserName);
           search.setName(name);
            list = order.orderViewByName(search);
        }
        Map<Object, Object> map2 = new HashMap<>();
        map2.put("mid",id);
     //   list.add(map2);
        return list;
    }

    /**
     * 查看车的类型
     * @return
     */
    @RequestMapping("/findMake")
    @ResponseBody
    public List<Product> findMake(){
        List<Product> list = order.findMake();
        return list;
    }



}
