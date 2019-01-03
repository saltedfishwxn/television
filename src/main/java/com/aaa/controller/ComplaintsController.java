package com.aaa.controller;
/**
 * @ClassName ComplaintsController
 * @Author Lxl
 * @Date 2018/12/24 14:19
 **/
import com.aaa.entity.Complaints;
import com.aaa.entity.LxlEcharts;
import com.aaa.service.ComplaintsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/complaints")
public class ComplaintsController extends BaseController{

    @Autowired
    private ComplaintsService service;

    @Value("${fileupload.path}")
    private String filePath;

    /**
     * 展示投诉页面
     * @return
     */
    @RequestMapping("/show")
    public String showComplaint(){
        return "complaints";
    }

    /**
     * getAttribute
     * @return
     */
    @RequestMapping("/getAttribute")
    @ResponseBody
    public Map<String,Object> getAttribute(){
        return service.getUserByLoginName(getShiroUserName());
    }


    /**
     * 展示统计图信息
     * @return
     */
    @RequestMapping("/showEcharts")
    public String showEcharts(){
        return "echarts";
    }

    /**
     * 前台点击处理信息 改变此投诉状态为正在处理
     * @return
     */
    @RequestMapping("/updateInfo")
    @ResponseBody
    public Map<String,Object> updateInfo(@RequestBody Map<String,Object> query){
        return service.updateInfo(query);
    }

    /**
     * 前台点击完成按钮  改变此投诉状态为正在处理
     * @return
     */
    @RequestMapping("/submitResult")
    @ResponseBody
    public Map<String,Object> submitResult(@RequestBody Complaints complaints){
        return service.submitResult(complaints);
    }

    /**
     * 前台点击通知客户按钮  改变此投诉状态为已通知
     * @param complaints
     * @return
     */
    @RequestMapping("/submitSendMsgForm")
    @ResponseBody
    public Map<String,Object> submitSendMsgForm(@RequestBody Complaints complaints){
        return service.submitSendMsgForm(complaints);
    }

    /**
     * 前台数据表格展示信息
     * @return
     */
    @RequestMapping("/listComplaints")
    @ResponseBody
    public Map<String,Object> listComplaints(@RequestBody Map<String,Object> query){
        return service.listComplaints(query);
    }

    /**
     * 前台下拉框 选择客户名 信息
     * @return
     */
    @RequestMapping("/listCustoms")
    @ResponseBody
    public Map<String,Object> listCustoms(){
        return service.listCustoms();
    }

    /**
     * 前台下拉框 选择员工名 信息
     * @return
     */
    @RequestMapping("/listUsers")
    @ResponseBody
    public Map<String,Object> listUsers(){
        return service.listUsers();
    }

    /**
     * 新建投诉投诉类型为产品投诉 判断有无此成交编号
     * 参数 订单编号 cord sorder
     * 返回的map key为addSeenYiInfo
     */
    @RequestMapping("/addSeenYi")
    @ResponseBody
    public Map<String,Object> addSeenYi(@RequestBody Map<String,Object> query){
        return service.addSeenYi(query);
    }

    /**
     * 新建投诉投诉类型为服务投诉 判断有无此员工
     * 参数 员工姓名
     * 返回的map key为addSeenErInfo 并它的id
     */
    @RequestMapping("/addSeenEr")
    @ResponseBody
    public Map<String,Object> addSeenEr(@RequestBody Map<String,Object> query){
        return service.addSeenEr(query);
    }

    /**
     * 删除员工 假删除 isDel：0改为1
     * @return
     */
    @RequestMapping("/deleteComplaintsById")
    @ResponseBody
    public Map<String,Object> deleteComplaintsById(Integer id){
        return service.deleteComplaintsById(id);
    }

    /**
     * 新增投诉
     * @return
     */
    @RequestMapping("/addComplaints")
    @ResponseBody
    public Map<String,Object> addComplaints(@RequestBody Complaints complaints){
        return service.addComplaints(complaints);
    }

    /**
     * 编辑投诉
     * @return
     */
    @RequestMapping("/editComplaints")
    @ResponseBody
    public Map<String,Object> editComplaints(@RequestBody Complaints complaints){
        return service.editComplaints(complaints);
    }

    /**
     * 导入表格
     * @return
     */
    @RequestMapping("/inExcel")
    @ResponseBody
    public Map<String, Object> inExcel(@RequestParam MultipartFile file) throws Exception {
        int ret = 0;
        Map map2 = upload(file,filePath);
        String a = map2.get("url").toString();
        String path=filePath+a;
        return service.inExcel(path);
    }

    /**
     * 导出表格
     * @return
     */
    @RequestMapping("toExcel")
    @ResponseBody
    public Map<String,Object> toExcel(@RequestBody Map<String,List<Complaints>> query) throws Exception{
        return service.toExcel(query);
    }

    /**
     * 统计被投诉订单编号和员工
     * @param echarts
     * @return
     */
    @RequestMapping("/doStatOth")
    @ResponseBody
    public Map<String,Object> doStatOth(@RequestBody LxlEcharts echarts){
        return service.doStatOth(echarts);
    }

    /**
     * 统计其他投诉
     * @param echarts
     * @return
     */
    @RequestMapping("/doStat")
    @ResponseBody
    public String doStat(@RequestBody LxlEcharts echarts){
        return service.doStat(echarts);
    }

    /**
     * 统计总图展示部分
     * @return
     */
    @RequestMapping("/statShow")
    public String statShow(){
        return "lxlStatShow";
    }




}
