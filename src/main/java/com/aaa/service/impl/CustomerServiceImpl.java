package com.aaa.service.impl;

import com.aaa.dao.CustomerMapper;
import com.aaa.entity.Customer;
import com.aaa.service.ICustomerService;
import com.aaa.util.MyException;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CustomerServiceImpl
 * @Description 用户信息管理service实现类
 * @Author LP
 * @Date 2018/12/15 14:10
 * @Version 1.0
 **/
@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 按照查询条件查询用户信息列表
     * @param query
     * @return
     */
    @Override
    public Map<String, Object> listCustomer(Map<String, Object> query) {
        Map<String,Object> map=new HashMap<String, Object>();
        int pageNo = (Integer) query.get("pageNo");
        int pageSize = (Integer) query.get("pageSize");
        PageHelper.startPage(pageNo,pageSize);
        map.put("rowList",customerMapper.listCustomer(query));
        map.put("totalList",customerMapper.countCustomer(query));
        return map;
    }

    /**
     * 按照id查询用户信息
     * @param customerId
     * @return
     */
    @Override
    public Map<String, Object> getCustomer(String customerId,String userName) {
        Map map=new HashMap();
        map.put("customerId",customerId);
        map.put("userName",userName);
        return customerMapper.getCustomer(map);
    }

    /**
     * 按照客户id对客户逻辑删除
     * @param customerId
     */
    @Override
    public void delCustomer(String customerId,String userId) {
        Map<String,Object> map=new HashMap();
        map.put("customerId",customerId);
        map.put("userId",userId);
        customerMapper.delCustomer(map);
    }

    /**
     * 新增客户信息
     * @param customer
     */
    @Override
    public int addCustomer(Customer customer) throws MyException{
        //判断是否客户重复
        Map countMap = customerMapper.isExist(customer.getFirsttel());
        if(((Number)countMap.get("count")).intValue()!=0){
            throw new MyException("此用户号码已经存在");
        }
        return customerMapper.addCustomer(customer);
    }

    /**
     * 更新客户信息
     * @param customer
     */
    @Override
    public void updateCustomer(Customer customer) {
        customerMapper.updateCustomer(customer);
    }

    /**
     * 新增客户员工关联
     * @param empid
     * @param customerid
     */
    @Override
    public void addCustomerToUser(Integer empid, Integer customerid) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("empid",empid);
        map.put("customerid",customerid);
        customerMapper.addCustomerToUser( map);
    }

    /**
     * 获取登录的用户信息
     * @param shiroUserName
     * @return
     */
    @Override
    public Map<String, Object> getLoginInfo(String shiroUserName) {
        return customerMapper.getLoginInfo(shiroUserName);
    }

    /**
     * 销售员选择客户
     * @param selMap
     */
    @Override
    @Transactional
    public void selCustomer(Map selMap) {
        //为用户选择客户添加关联
        customerMapper.selCustomer(selMap);
        //修改被选择的客户，不在是公共客户
        customerMapper.changePublic(selMap.get("customerId"));
    }

    /**
     * 选择客户时判断是否已经被选择了
     * @param customerId
     * @return
     */
    @Override
    public boolean isSel(String customerId) {
        Map<String, Object> sel = customerMapper.isSel(customerId);
        if((int)sel.get("isPublic")!=0){
            return true;
        }
        return false;
    }

    /**
     * 批量插入时插入一条客户数据
     * @param map
     */
    @Override
    public void addCustomers(Map<String, Object> map) throws MyException {
        //判断是否客户重复
        Map countMap = customerMapper.isExist(map.get("firsttel").toString());
        if(((Number)countMap.get("count")).intValue()!=0){
            throw new MyException("此用户号码已经存在");
        }
        //插入用户并返回主键
       customerMapper.addCustomers(map);
       /*判断是否是公共客户，如果不是，插入客户用户关联关系*/
        if((int)map.get("isPublic")!=1){
            Integer customerid =((Number)map.get("customerId")).intValue();
            Map<String, Object> login = getLoginInfo(map.get("loginName").toString());
            //Integer属于不可更改类型，而且Long和Integer没有任何继承关系，当然不能这样转换
            //注:java.lang.Number是Integer,Long的父类.
            addCustomerToUser(((Number)login.get("userId")).intValue(),customerid);
        }
    }

    /**
     * 客户来源统计
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> customerResourceCount(Map<String,Object> map) {
        List<Map<String, Object>> list = customerMapper.customerResourceCount(map);
        Map<String, Object> returnMap = returnManager(list);
        return returnMap;
    }

    /**
     * 客户量统计
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> customerCount(Map<String, Object> map) {
        System.out.println(map);
        List<Map<String, Object>> list = customerMapper.customerCount(map);
        Map<String, Object> returnMap = returnManager(list);
        return returnMap;
    }

    /**
     * 查询产品列表
     * @return
     */
    @Override
    public List<Map<String, Object>> listProducts() {
        return customerMapper.listProducts();
    }


    /**
     * 查询返回数据管理
     */
    private Map<String,Object> returnManager(List<Map<String,Object>> list){
        Map<String,Object> returnMap=new HashMap();
        String[] name=new String[list.size()];
        String[] count=new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            name[i]=String.valueOf( list.get(i).get("name"));
            count[i]=String.valueOf( list.get(i).get("count"));
        }
        returnMap.put("name",name);
        returnMap.put("count",count);
        return  returnMap;
    }
    /**
     * 当新增客户信息时,使用该方法添加一条回访信息到followinfo表
     * 需要三个信息 新增客户的id customerId ,当前日期 nextFollowTime ,回访状态 isDone
     * @return
     */
    @Override
    public int insertNewFollowInfo( String customerId) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowDate = sdf.format(date);//获取当前时间作为followInfo表的nextFollowTime
        int isDone = 1;//初始回访状态为1 意为未回访状态  0是已回访 该处使用1
        Map<String, Object> map = new HashMap<>();
        map.put("customerId",customerId);
        map.put("nextFollowTime",nowDate);
        map.put("isDone",isDone);
        return customerMapper.insertNewFollowInfo(map);
    }

}
