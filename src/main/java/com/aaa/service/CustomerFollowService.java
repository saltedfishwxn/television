package com.aaa.service;

import com.aaa.query.FollowInfo;
import com.aaa.query.FollowQuery;

import java.util.List;
import java.util.Map;

/**
 * code is far away from bug with the animal protecting
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Description :
 * ---------------------------------
 * @Author : An
 * @Date : Create in 2018/12/18 001816:42
 */
public interface CustomerFollowService {
    Map listNotVisited(Map<String, Object> query);

    /**
     * 使用客户id与用户id确定该客户
     * @param
     * @return
     */
    Map getFollowInfoByCustomerId(Integer cid, Integer uid);

    Map listFollowQuery(FollowQuery followQuery, Map map);

    int insertFollowInfo(FollowInfo followInfo);

    /**
     * 通过客户id查询跟进历史记录
     * @param customerId
     * @return
     */
    List<Map> getFollowHistory(int customerId);

    /**
     * 查询所有属于业务员的员工
     * @return
     */
    List<Map> findUsers(int id);

    /**
     * 将需要分享的客户插入到user_customer中
     * @param map
     * @return
     */
    int insertShareCustomer(Map<String, Object> map);
    /**
     * 当新增客户信息时,使用该方法添加一条回访信息到followinfo表
     * for:lipeng
     */
    int insertNewFollowInfo();

    /**
     * 根据用户登录名shiroUserName 判断员工身份 将员工roleid返回
     * @param shiroUserName
     * @return
     */
    Map checkEmpRoleName(String shiroUserName);

    /**
     * 根据map获取应查到的待回访数量
     * @param map
     * @return
     */
    int getCountNeedFollow(Map map);

    /**
     * 查询所有共享的客户
     * @return
     */
    List<Map> listShareCustomer(Map map);

    /**
     * 根据前台传递的客户id取消客户共享
     * @param cid
     * @return
     */
    int cancelShareCustomer(Integer cid);

    /**
     * 查询客户基盘数量
     * @param
     * @return
     */
    List<Map> listCustomerCountByLevel(int rid, int uid);

    /**
     * 查询客户是否已存在
     * @param cusId
     * @param shareId
     * @return
     */
    int customerIsExist(int cusId, int shareId);
}
