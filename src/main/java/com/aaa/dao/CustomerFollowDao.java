package com.aaa.dao;

import com.aaa.query.FollowInfo;
import com.aaa.query.FollowQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
 * @Description :客户跟进dao接口
 * ---------------------------------
 * @Author : An
 * @Date : Create in 2018/12/18 001816:38
 */
@Mapper
public interface CustomerFollowDao {
    /**
     * 查找未回访的客户
     * @param query
     * @return
     */
    List<Map>listNotVisited(Map<String, Object> query);

    /**
     * 通过客户id与empId来查找某一个客户信息
     * @param id
     * @param uid
     * @return
     */
    Map getFollowInfoByCustomerId(@Param("cid") Integer id, @Param("uid") Integer uid);

    /**
     * 通过followQuery 来选择数据返回
     * @param followQuery
     * @return
     */
    List<Map> listFollowQuery(FollowQuery followQuery);

    /**
     * 获取未回访的数据总量,与上一条结合使用
     * @param followQuery
     * @return
     */
    int getTotalQuery(FollowQuery followQuery);

    /**
     * 插入下次回访的信息
     * @param followInfo
     * @return
     */
    int insertFollowInfo(FollowInfo followInfo);

    /**
     * 根据客户id查询客户跟进历史记录
     * @param customerId
     * @return
     */
    List<Map> getFollowHistory(int customerId);

    /**
     * 查询所有业务员信息
     * @return
     */
    List<Map> findUsers(int id);

    /**
     * 将需要分享的客户插入到user_customer中
     * @param map
     * @return 受影响的行数
     */
    int insertShareCustomer(Map<String, Object> map);

    /**
     * 通过customerId查询followId
     * @param customerId
     * @param i
     * @return
     */
    int getFollowIdByCustomerId(int customerId, int i);

    /**
     * 改变某行数据的回访状态 通过followId
     * @param id
     * @return
     */
    int changeIsDoneStatus(int id);

    /**
     * 根据新的客户等级修改客户表客户等级
     * @param condition
     * @return
     */
    int updateCustomerLevel(Map condition);

    /**
     * 通过followInfo完善信息
     * @param followInfo
     * @return
     */
    int updateFollowInfo(FollowInfo followInfo);
    /**
     * 当新增客户信息时,使用该方法添加一条回访信息到followinfo表
     */
    int insertNewFollowInfo(Map<String, Object> map);

    /**
     *根据用户登录名shiroUserName 判断员工身份 将员工roleid返回
     * @param shiroUserName
     * @return
     */
    Map checkEmpRoleName(String shiroUserName);

    /**
     * 将id (userId)作为条件查询需跟进的数量
     * @param id
     * @return
     */
    int getCountNeedFollowSelf(int id);

    /**
     * 销售经理查看 查询所有未跟进的客户总量
     * @return
     */
    int getCountNeedFollowAll();

    /**
     * 查询某员工下所有共享的客户
     * @return
     */
    List<Map> listShareCustomer(int uid);

    /**
     * 非员工 所有的共享客户
     * @return
     */
    List<Map> listShareCustomerNone();

    /**
     *根据前台传递的客户id取消客户共享
     * @param cid
     * @return
     */
    int cancelShareCustomer(Integer cid);

    /**
     * 按身份查询基盘客户数据
     * @param rid
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
