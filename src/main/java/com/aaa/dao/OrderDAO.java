package com.aaa.dao;

import com.aaa.entity.Customer;
import com.aaa.entity.Order;
import com.aaa.entity.OrderSearch;
import com.aaa.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Demo class
 * 合同信息
 * @author 尚冠峰
 * @date 2018/12/19
 */
@Mapper
public interface OrderDAO {
    List<Product> findMake();

    int addOrder(Order order);

    List<Map> orderView(OrderSearch search);

    List<Map> allView(Map map);

    int getTotal(Map map);

    Customer getOrder(int i);

    String getName(String shiroUserName);

    int getidByName(String shiroUserName);

    List<Map> allViewByName(Map<String, Object> map);

    int getTotelByName(Map map);

    List<Map> orderViewByName(OrderSearch search);
}
