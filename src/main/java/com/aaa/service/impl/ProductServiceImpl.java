package com.aaa.service.impl;

import com.aaa.dao.ProductMapper;
import com.aaa.entity.Product;
import com.aaa.service.IProductService;
import com.aaa.util.MyException;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: ProductServiceImpl
 * Description:
 * date: 2018/12/17 14:31
 *
 * @author 王晓楠
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class ProductServiceImpl implements IProductService{
    @Autowired
    private ProductMapper productMapper;

    /**
     * 查询所有产品信息
     * @param query
     * @return
     */
    @Override
    public Map findAll(Map<String, Object> query) {
        int pageNo=Integer.parseInt(query.get("pageNo")+"");
        int pageSize=Integer.parseInt(query.get("pageSize")+"");
        Map amp =(Map)query.get("form");
        PageHelper.startPage(pageNo,pageSize);
        Map map=new HashMap();
        map.put("rowList",productMapper.findAllByPage(amp));
        map.put("getTotal",productMapper.getTotal(amp));
        return map;
    }

    /**
     * 添加产品
     * @param product
     * @return
     */
    @Transactional
    @Override
    public int addProduct(Product product) throws MyException {
        //判断是否客户重复
        Map map=new HashMap();
        map.put("type",product.getType());
        map.put("bodyColor",product.getBodycolor());
        map.put("interiorColor",product.getInteriorcolor());
        Map countMap = productMapper.isExist(map);
        if(((Number)countMap.get("count")).intValue()!=0){
            throw new MyException("此产品已经存在");
        }
        return productMapper.addProduct(product);
    }

    /**
     * 查询品牌
     * @return
     */
    @Override
    public List<Product> findMake() {
        return productMapper.findMake();
    }
    /**
     * 查询内饰颜色
     * @return
     */
    @Override
    public List<Product> findInteriorColor() {
        return productMapper.findInteriorColor();
    }
    /**
     * 查询外观颜色
     * @return
     */
    @Override
    public List<Product> findBodyColor() {
        return productMapper.findBodyColor();
    }
    /**
     * 查询变速箱
     * @return
     */
    @Override
    public List<Product> findTransmission() {
        return productMapper.findTransmission();
    }
    /**
     * 查询发动机
     * @return
     */
    @Override
    public List<Product> findEngine() {
        return productMapper.findEngine();
    }
    /**
     * 查询车身
     * @return
     */
    @Override
    public List<Product> findBody() {
        return productMapper.findBody();
    }
    /**
     * 查询款型
     * @return
     */
    @Override
    public List<Product> findType(Product product) {
        return productMapper.findType(product);
    }
    /**
     * 查询车系
     * @return
     */
    @Override
    public List<Product> findLine(Product product) {
        return productMapper.findLine(product);
    }
    /**
     * 查询产品状态
     * @return
     */
    @Override
    public List<Product> findPsatus() {
        return productMapper.findPstatus();
    }

    /**
     * 修改产品
     * @param map
     * @return
     */
    @Override
    public int updateProduct(Map map) {
        return productMapper.updateProduct(map);
    }

    /**
     * 删除产品
     * @param delLists
     * @return
     */
    @Transactional
    @Override
    public int delProduct(int[] delLists){

        int a=0;
        try {
            for(int i=0;i<delLists.length;i++){
                productMapper.delProduct(delLists[i]);
                a++;
            }
            return a;
        } catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }

    /**
     * 查询产品是否存在
     * @param product
     * @return
     */
    @Override
    public Product findCheckBody(Product product) {
        return productMapper.findCheckBody(product);
    }

    /**
     * 查询产品状态(条件查询)
     * @param product
     * @return
     */
    @Override
    public List<Product> findPstatusTwo(Product product) {
        return productMapper.findPstatusTwo(product);
    }

    /**
     * 查询产品数量
     * @param map
     * @return
     */
    @Override
    public List<Map<String,Object>> productCount(Map map) {
        List<Map<String,Object>> list=productMapper.productCount(map);
//        Map<String,Object> returnMap = returnManager(list);
        return list;
    }

    /**
     * 获取登录的用户信息
     * @param shiroUserName
     * @return
     */
    @Override
    public Map<String, Object> getLoginInfo(String shiroUserName) {
        return productMapper.getLoginInfo(shiroUserName);
    }

    /**
     * 批量插入时插入一条产品数据
     * @param map
     */
    @Transactional
    @Override
    public void addProductMany(Map<String, Object> map) throws MyException {
        //判断是否客户重复
        Map countMap = productMapper.isExist(map);
        if(((Number)countMap.get("count")).intValue()!=0){
            throw new MyException("此产品已经存在");
        }
        //获取负责人昵称并插入

        map.put("createPerson",(productMapper.getLoginInfo(map.get("loginName").toString())).get("name"));
        //插入用户并返回主键
        productMapper.addProductMany(map);
        //Map<String, Object> login = getLoginInfo(map.get("loginName").toString());
        //Integer属于不可更改类型，而且Long和Integer没有任何继承关系，当然不能这样转换
        //注:java.lang.Number是Integer,Long的父类.
    }
    /**
     * 查询返回数据管理
     */
    /*private Map<String,Object> returnManager(List<Map<String,Object>> list){
        Map<String,Object> returnMap=new HashMap();
        String[] make=new String[list.size()];
        String[] line=new String[list.size()];
        String[] type=new String[list.size()];
        String[] bodyColor=new String[list.size()];
        String[] pproCount=new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            make[i]=String.valueOf( list.get(i).get("make"));
            line[i]=String.valueOf( list.get(i).get("line"));
            type[i]=String.valueOf( list.get(i).get("type"));
            bodyColor[i]=String.valueOf( list.get(i).get("bodyColor"));
            pproCount[i]=String.valueOf( list.get(i).get("pproCount"));
        }
        returnMap.put("make",make);
        returnMap.put("line",line);
        returnMap.put("type",type);
        returnMap.put("bodyColor",bodyColor);
        returnMap.put("pproCount",pproCount);
        return  returnMap;
    }*/
}
