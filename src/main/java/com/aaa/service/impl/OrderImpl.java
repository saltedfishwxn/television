package com.aaa.service.impl;

import com.aaa.dao.OrderDAO;
import com.aaa.entity.Customer;
import com.aaa.entity.Order;
import com.aaa.entity.OrderSearch;
import com.aaa.entity.Product;
import com.aaa.service.OrderBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Demo class
 * 合同信息
 * @author 尚冠峰
 * @date 2018/12/19
 */
@Service
public class OrderImpl implements OrderBiz {
    @Autowired
    private OrderDAO dao;
    @Override
    public List<Product> findMake() {
        return dao.findMake();
    }

    @Override
    public int  addOrder(Order order) {

        System.out.println(order.toString());
        int i = dao.addOrder(order);
        return i;
    }

    @Override
    public List<Map> orderView(OrderSearch search) {
        return dao.orderView(search);

    }

    @Override
    public List<Map> allView(Map map) {
        Object pageSize = map.get("pageSize");
        Object pageNo = map.get("pageNo");
        int pn = Integer.parseInt(String.valueOf(pageNo));
       int ps =Integer.parseInt(String.valueOf(pageSize));
       int begin=(pn-1)*ps;
       map.put("begin",begin);
        List<Map> maps = dao.allView(map);
        System.out.println(maps.get(0).get("url"));
        System.out.println(maps);
        return maps;
    }

    @Override
    public int getTotel(Map map) {
        return dao.getTotal(map);
    }

    @Override
    public Customer getOrder(int i) {
        return dao.getOrder(i);
    }

    @Override
    public String getName(String shiroUserName) {
        return dao.getName(shiroUserName);
    }

    @Override
    public int getidByName(String shiroUserName) {
        return dao.getidByName(shiroUserName);
    }

    @Override
    public List<Map> allViewByName(Map<String, Object> map) {
        return dao.allViewByName(map);
    }

    @Override
    public int getTotelByName(Map map) {
       return dao.getTotelByName(map);
    }

    @Override
    public List<Map> orderViewByName(OrderSearch search) {
        return dao.orderViewByName(search);
    }
}
