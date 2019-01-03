package com.aaa.service;

import com.aaa.entity.Customer;
import com.aaa.util.MyException;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ICustomerService
 * @Description 用户信息管理service接口
 * @Author LP
 * @Date 2018/12/15 14:10
 * @Version 1.0
 **/
public interface ICustomerService {
    /**
     * 按照查询条件查询用户列表
     * @param query
     * @return
     */
    Map<String, Object> listCustomer(Map<String, Object> query);

    /**
     * 按照客户id查询客户信息
     * @param customerId
     * @return
     */
    Map<String, Object> getCustomer(String customerId, String userName);

    /**
     * 按照客户id对客户信息进行逻辑删除
     * @param customerId
     */
    void delCustomer(String customerId, String userId);

    /**
     * 添加客户信息
     * @param customer
     */
    int addCustomer(Customer customer) throws MyException;

    /**
     * 修改客户信息
     * @param customer
     */
    void updateCustomer(Customer customer);

    /**
     * 客户批量添加
     * @param map
     * @throws MyException
     */
    void addCustomers(Map<String, Object> map) throws MyException;

    /**
     * 客户来源统计
     * @param map
     * @return
     */
    Map<String, Object> customerResourceCount(Map<String, Object> map);

    /**
     * 客户量统计
     * @param map
     * @return
     */
    Map<String, Object> customerCount(Map<String, Object> map);

    /**
     * 获取所有产品信息
     * @return
     */
    List<Map<String, Object>> listProducts();

    /**
     * 添加客户与业务员之间的关联
     * @param empId
     * @param customerId
     */
    void addCustomerToUser(Integer empId, Integer customerId);

    /**
     * 获取登录信息
     * @param shiroUserName
     * @return
     */
    Map<String, Object> getLoginInfo(String shiroUserName);

    /**
     * 选择共享的客户
     * @param selMap
     */
    void selCustomer(Map selMap);

    /**
     * 选择客户时判断是否已经被选择了
     * @param customerId
     * @return
     */
    boolean isSel(String customerId);

    /**
     * 添加跟进记录
     * @return
     */
    int insertNewFollowInfo(String customerId);
}
