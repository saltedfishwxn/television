package com.aaa.service;

import com.aaa.entity.Product;
import com.aaa.util.MyException;

import java.util.List;
import java.util.Map;

/**
 * ClassName: IProductService
 * Description:产品信息管理Service
 * date: 2018/12/17 14:31
 *
 * @author 王晓楠
 * @version 1.0
 * @since JDK 1.8
 */
public interface IProductService {
    /**
     * 查询所有产品信息
     * @param query
     * @return
     */
    Map findAll(Map<String, Object> query);

    /**
     * 添加产品
     * @param product
     * @return
     */
    int addProduct(Product product) throws MyException;

    /**
     * 产品品牌查询
     * @return
     */
    List<Product> findMake();
    /**
     * 查询内饰颜色
     * @return
     */
    List<Product> findInteriorColor();
    /**
     * 查询外观颜色
     * @return
     */
    List<Product> findBodyColor();
    /**
     * 查询变速箱
     * @return
     */
    List<Product> findTransmission();
    /**
     * 查询发动机
     * @return
     */
    List<Product> findEngine();
    /**
     * 查询车身
     * @return
     */
    List<Product> findBody();
    /**
     * 查询款型
     * @return
     */
    List<Product> findType(Product product);
    /**
     * 查询车系
     * @return
     */
    List<Product> findLine(Product product);
    /**
     * 查询产品状态
     * @return
     */
    List<Product> findPsatus();

    /**
     * 修改产品信息
     * @param map
     * @return
     */
    int updateProduct(Map map);

    /**
     * 删除产品
     * @param delLists
     * @return
     */
    int delProduct(int[] delLists);

    /**
     * 查询产品是否存在
     * @param product
     * @return
     */
    Product findCheckBody(Product product);
    /**
     *查询产品状态(条件查询)
     * @param product
     * @return
     */
    List<Product> findPstatusTwo(Product product);

    /**
     * 查询产品数量
     * @param map
     * @return
     */
    List<Map<String,Object>> productCount(Map map);

    /**
     * 登录信息
     * @param shiroUserName
     * @return
     */
    Map<String, Object> getLoginInfo(String shiroUserName);

    /**
     * 批量添加
     * @param map
     * @throws MyException
     */
    void addProductMany(Map<String, Object> map) throws MyException;

}
