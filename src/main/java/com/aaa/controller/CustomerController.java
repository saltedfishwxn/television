package com.aaa.controller;

import com.aaa.entity.Customer;
import com.aaa.entity.CustomerOutEntity;
import com.aaa.entity.ResultModel;
import com.aaa.service.ICustomerService;
import com.aaa.util.CustomerExcelIn;
import com.aaa.util.ExcelOutCommin;
import com.aaa.util.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CustomerController
 * @Description 管理操控客户信息的controller
 * @Author LP
 * @Date 2018/12/15 14:02
 * @Version 1.0
 **/
@Slf4j//log定义logger对象用于输出
@Controller
@RequestMapping("/cstm")
public class CustomerController extends BaseController {
    @Autowired
    private ICustomerService customerService;
    @Value("D:/fileUpload/")
    private String uploadPictureFilePath;

    /**
     * 页面跳转
     * @return
     */
    @RequestMapping("/jump")
    public String customer() {
        return "customerInfo";
    }

    /**
     * 按查询条件展示客户信息列表
     * @param query
     * @return
     */
    @RequestMapping("/listCustomer")
    @ResponseBody
    public Map<String, Object> listCustomer(@RequestBody Map<String,Object> query){
        //将当前客户的id加入判断
        Map<String, Object> loginInfo = getLoginInfo();
        query.put("userId",loginInfo.get("userId"));
        query.put("rId",loginInfo.get("rId"));
        log.debug("查询用户======================================>");
        return customerService.listCustomer(query);
    }

    /**
     * 按照客户id查询客户的信息
     * @param customerId
     * @return
     */
    @RequestMapping("/getCustomer")
    @ResponseBody
    public Map<String, Object> getCustomer(String customerId,String userName){

        return customerService.getCustomer(customerId,userName);
    }
    /**
     * 对客户进行逻辑删除操作
     * @param customerId
     * @return
     */
    @RequestMapping("/delCustomer")
    @ResponseBody
    public ResultModel delCustomer(String customerId, String userId){
        try{
            customerService.delCustomer(customerId,userId);
        }catch (Exception e){
            return returnError("删除失败");
        }
        return returnSuccess("删除成功");
    }

    /**
     * 添加新的客户信息
     * @param customer
     * @return
     */
    @RequestMapping("/addCustomer")
    @ResponseBody
    public ResultModel addCustomer(@RequestBody Customer customer){
        try{
            //添加用户并返回主键
           customerService.addCustomer(customer);
           //添加客户，用户关联
            if(customer.getUserId()!=null){
                customerService.addCustomerToUser(customer.getUserId(),customer.getCustomerid());
                customerService.insertNewFollowInfo(customer.getCustomerid().toString());
            }
        }catch (MyException e){
            return returnError("此用户号码已存在");
        }catch (Exception e){
            return returnError("添加失败");
        }
        return returnSuccess("添加成功");
    }

    /**
     * 更新客户信息
     * @param customer
     * @return
     */
    @RequestMapping("/updateCustomer")
    @ResponseBody
    public ResultModel updateCustomer(@RequestBody Customer customer){
        try{
           customerService.updateCustomer(customer);
        }catch (Exception e){
            return returnError("修改失败");
        }
        return returnSuccess("修改成功");
    }
    /**
     * 非共享客户信息批量添加
     * @param file
     * @return
     * 添加事务
     */
    @RequestMapping("/customerUpload")
    @ResponseBody
    public ResultModel customerUpload(MultipartFile file){
        Integer isPublic=0;
        return uploadManager(file,isPublic);
    }
    /**
     * 共享客户信息批量添加
     * @param file
     * @return
     * 添加事务
     */
    @RequestMapping("/publicCustomerUpload")
    @ResponseBody
    public ResultModel publicCustomerUpload(MultipartFile file){
        Integer isPublic=1;
        return uploadManager(file,isPublic);
    }

    /**
     * 后续要追加客户负责人作为参数传递进去，判断是从哪个页面导入的表格。。。后续任务标记。。。
     * 文件上传处理及是否为公共订单的判断
     * 1,接收上传的Excel表格和前台的是否是公共客户上传的标记
     * 2，将文件上传到指定目录
     * 3，将文件读取成List<Map>并将是否公共的标记插入
     * 4，执行客户的信息插入
     * @param file
     * @param isPublic
     * @return
     */
    ResultModel uploadManager(MultipartFile file, Integer isPublic){
        //上传至服务器的路径
        String fileName = file.getOriginalFilename();
        File targetFile= new File(uploadPictureFilePath,fileName);
        try {
            //将文件拷贝到指定的位置
            file.transferTo(targetFile);
            //log.debug("文件上传成功");
        } catch (IOException e) {
            // log.debug("文件上传失败的原因："+e.getMessage());
            //e.printStackTrace();
            return returnError("文件上传失败");
        }
        List<Map<String, Object>> list;
        try {
            list = CustomerExcelIn.readXLSX(uploadPictureFilePath+ fileName);
        } catch (Exception e) {
            return returnError("文件读取失败");
        }
        try {
          for(int i=0;i<list.size();i++){
                Map<String, Object> map = list.get(i);
              //将是否为公共客户字段加入添加参数
              map.put("isPublic",isPublic);
              map.put("loginName",getShiroUserName());
              customerService.addCustomers(map);
              Integer customerid =((Number)map.get("customerId")).intValue();
              customerService.insertNewFollowInfo(customerid.toString());
          }
        }catch (MyException e){
            return returnError("有用户导入失败,检查格式是否正确");
        }catch (Exception e){
            return returnError("文件上传失败,检查格式是否正确");
        }
        return returnSuccess("文件上传成功");
    }

    /**
     * 客户统计页跳转
     * @return
     */
    @RequestMapping("/statistical")
    public String statisticalAnalysis(){
        return "customerResource";
    }

    /**
     * 客户来源统计
     * @param queryStyle
     * @param beginTime
     * @param endTime
     * @return
     */
    @ResponseBody
    @RequestMapping("/customerResourceCount")
    public Map<String,Object> customerResourceCount(String queryStyle, Date beginTime, Date endTime){
        Map<String, Object> map = timeManage(queryStyle, beginTime, endTime);
        return customerService.customerResourceCount(map);
    }

    /**
     * 客户量统计
     * @param queryStyle
     * @param beginTime
     * @param endTime
     * @return
     */
    @RequestMapping("customerCount")
    @ResponseBody
    public Map<String,Object> customerCount(String queryStyle, Date beginTime, Date endTime){
        Map<String, Object> map = timeManage(queryStyle, beginTime, endTime);
        return customerService.customerCount(map);
    }

    /**
     * 查询参数的时间处理
     * @param queryStyle
     * @param beginTime
     * @param endTime
     * @return
     */
   private Map<String,Object> timeManage(String queryStyle, Date beginTime, Date endTime){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Map<String,Object> map=new HashMap();
        String startTime=null;
        String overTime=null;
        if(beginTime!=null){
            startTime = format.format(beginTime);
            map.put("queryStyle",queryStyle);
            map.put("beginTime",startTime);
        }
        if(endTime!=null){
            overTime = format.format(endTime);
            map.put("endTime",overTime);
        }
        return map;
    }

    /**
     * 公共客户页面 跳转
     * @return
     */
    @RequestMapping("/publicCustomer")
    public String publicCustomer(){
       return "publicCustomerInfo";
    }

    /**
     * 获取所有产品信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/listProducts")
    public List<Map<String,Object>> listProducts(){
       return customerService.listProducts();
    }

    /**
     * 获取登录人信息
     */
    @ResponseBody
    @RequestMapping("/getLoginInfo")
    public  Map<String,Object> getLoginInfo(){
        return customerService.getLoginInfo(getShiroUserName());
    }

    /**
     * 客户批量导出
     * @param query
     * @param req
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/exportCustomerInExcel")
    public ResultModel exportCustomerInExcel (@RequestBody Map<String,List<CustomerOutEntity>> query, HttpServletRequest req) throws Exception{
        List<CustomerOutEntity> list=query.get("customers");
        System.out.println(list);
        OutputStream out = new FileOutputStream(uploadPictureFilePath+"aaa.xls");
        Map<String, String> fields = new HashMap<String, String>();
        fields.put("customerId", "客户编号");
        fields.put("customerName", "客户姓名");
        fields.put("sex", "性别");
        fields.put("firstTel", "联系方式");
        fields.put("secondTel", "备用联系方式");
        fields.put("address", "地址");
        fields.put("customerLevel", "客户级别");
        fields.put("customerSource", "客户来源");
        fields.put("job", "工作");
        fields.put("make", "意向产品（品牌）");
        fields.put("line", "意向产品（系列）");
        fields.put("type", "意向产品（种类）");
        fields.put("InteriorColor", "意向产品（内饰）");
        fields.put("createTime", "创建时间");
        fields.put("name", "负责人");
        ExcelOutCommin.ListtoExecl(list, out, fields);
        out.close();
        return returnSuccess("导出成功");
    }

    /**
     * 业务员选择公共客户
     * @param customerId
     * @return
     */
    @RequestMapping("/selCustomer")
    @ResponseBody
    public ResultModel selCustomer(String customerId){
        Map<String, Object> loginInfo = getLoginInfo();
        //获取当前登录用户id
        String  userId = String.valueOf(loginInfo.get("userId"));
        Map selMap=new HashMap();
        selMap.put("customerId",customerId);
        selMap.put("userId",userId);
        //判断是否已经被选择了，如果被选了返回并刷新
        boolean flag = customerService.isSel(customerId);
        if(!flag){
            return returnError("该用户已被选择");
        }
        try {
            customerService.selCustomer(selMap);
        }catch (Exception e){
          return returnError("选择失败");
        }
          return  returnSuccess("选择成功");
    }
}
