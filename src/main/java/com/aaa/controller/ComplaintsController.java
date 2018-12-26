package com.aaa.controller;

import com.aaa.entity.Complaints;
import com.aaa.entity.LxlEcharts;
import com.aaa.entity.ResultModel;
import com.aaa.service.ComplaintsService;
import com.aaa.util.LxlExcel;
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

    @RequestMapping("/show")
    public String showComplaint(){
        return "complaints";
    }

    @RequestMapping("/showEcharts")
    public String showEcharts(){
        return "echarts";
    }

    /**
     *
     */
    @RequestMapping("/updateInfo")
    @ResponseBody
    public Map<String,Object> updateInfo(@RequestBody Map<String,Object> query){
        return service.updateInfo(query);
    }

    @RequestMapping("/submitResult")
    @ResponseBody
    public Map<String,Object> submitResult(@RequestBody Complaints complaints){
        return service.submitResult(complaints);
    }

    @RequestMapping("/listComplaints")
    @ResponseBody
    public Map<String,Object> listComplaints(@RequestBody Map<String,Object> query){
        return service.listComplaints(query);
    }

    @RequestMapping("/listCustoms")
    @ResponseBody
    public Map<String,Object> listCustoms(){
        return service.listCustoms();
    }

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

    @RequestMapping("/deleteComplaintsById")
    @ResponseBody
    public Map<String,Object> deleteComplaintsById(Integer id){
        return service.deleteComplaintsById(id);
    }

    @RequestMapping("/addComplaints")
    @ResponseBody
    public Map<String,Object> addComplaints(@RequestBody Complaints complaints){
        return service.addComplaints(complaints);
    }

    @RequestMapping("/editComplaints")
    @ResponseBody
    public Map<String,Object> editComplaints(@RequestBody Complaints complaints){
        return service.editComplaints(complaints);
    }

    @RequestMapping("/inExcel")
    @ResponseBody
    public ResultModel inExcel(@RequestParam MultipartFile file) throws Exception {
        int ret = 0;
        Map map2 = upload(file,filePath);
        String a = map2.get("url").toString();
        String path=filePath+a;
        List<Map<String, Object>> map3 =  LxlExcel.readXLSX(path);
            service.inExcel(map3);
            ret = 1;
        if (ret>0){
            return returnSuccess("导入成功");
        }else {
            return returnError("导入失败");
        }
    }

    @RequestMapping("toExcel")
    @ResponseBody
    public Map<String,Object> toExcel(@RequestBody Map<String,List<Complaints>> query) throws Exception{
        return service.toExcel(query);
    }

    @RequestMapping("/doStatOth")
    @ResponseBody
    public Map<String,Object> doStatOth(@RequestBody LxlEcharts echarts){
        return service.doStatOth(echarts);
    }

    @RequestMapping("/doStat")
    @ResponseBody
    public String doStat(@RequestBody LxlEcharts echarts){
        return service.doStat(echarts);
    }





}
