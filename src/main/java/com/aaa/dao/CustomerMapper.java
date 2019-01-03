package com.aaa.dao;

import com.aaa.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CustomerController
 * @Description 管理操控客户信息的Mapper接口
 * @Author LP
 * @Date 2018/12/15 14:02
 * @Version 1.0
 **/
@Mapper
public interface CustomerMapper {
    /**
     *同于添加用户的接口
     */
    int insert(Customer record);

    /**
     * 按条件查询用户列表
     * @param query
     * @return
     */
    List<Map<String,Object>> listCustomer(Map<String, Object> query);

    /**
     * 条件查询用户的总数
     * @param query
     * @return
     */
    Integer countCustomer(Map<String, Object> query);

    /**
     * 按照id和负责人查询客户信息
     * @return
     */
    Map<String, Object> getCustomer(Map map);

    /**
     * 客户逻辑删除
     * @param map
     */
    void delCustomer(Map<String, Object> map);

    /**
     * 添加客户信息
     * @param customer
     */
    int addCustomer(Customer customer);

    /**
     * 更新客户信息
     * @param customer
     */
    void updateCustomer(Customer customer);

    /**
     * 表格导入添加客户
     * @param map
     * @return
     */
    Integer addCustomers(Map<String, Object> map);

    /**
     * 客户来源统计
     * @param map
     * @return
     */
    List<Map<String, Object>> customerResourceCount(Map<String, Object> map);

    /**
     * 客户量统计
     * @param map
     * @return
     */
    List<Map<String, Object>> customerCount(Map<String, Object> map);

    /**
     * 查询产品
     * @return
     */
    List<Map<String, Object>> listProducts();

    /**
     * 添加客户和用户的关联
     * @param map
     */
    void addCustomerToUser(Map<String, Object> map);

    /**
     * 获取登录信息
     * @param shiroUserName
     * @return
     */
    Map<String, Object> getLoginInfo(String shiroUserName);

    /**
     * 选择公共客户
     * @param selMap
     */
    void selCustomer(Map selMap);

    /**
     *选择客户之后更改公共标记
     * @param customerId
     */
    void changePublic(Object customerId);

    /**
     * 选择客户时判断是否已经被选过了
     * @param customerId
     * @return
     */
    Map<String,Object> isSel(String customerId);

    /**
     * 判断录入客户时此客户的电话是否已经存在
     * @param firstTel
     * @return
     */
    Map isExist(String firstTel);

    /**
     * 插入跟进记录
     * @param map
     * @return
     */
    int insertNewFollowInfo(Map<String, Object> map);
}