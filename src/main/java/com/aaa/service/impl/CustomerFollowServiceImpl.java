package com.aaa.service.impl;

import com.aaa.dao.CustomerFollowDao;
import com.aaa.query.FollowInfo;
import com.aaa.query.FollowQuery;
import com.aaa.service.CustomerFollowService;
import com.aaa.util.ReturnNextFollowDate;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
 * @Description :客户跟进service实现
 * ---------------------------------
 * @Author : An
 * @Date : Create in 2018/12/18 001816:43
 */
@Service
public class CustomerFollowServiceImpl implements CustomerFollowService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    CustomerFollowDao customerFollowDao;

    /**
     * 查询所有今日应当回访的客户信息
     * @return 所有今日应当回访的客户信息
     */
    @Override
    public Map listNotVisited(Map<String,Object> query) {
        int pageNo =Integer.parseInt(query.get("pageNo")+"");
        int pageSize =Integer.parseInt(query.get("pageSize")+"");
        PageHelper.startPage(pageNo, pageSize);
        Map map = new HashMap<>();
        map.put("rowList",customerFollowDao.listNotVisited(query));
        //map.put("totalRow",customerFollowDao.getTotal());
        return map;
    }

    /**
     * 通过id获取跟进信息
     * @param id
     * @return
     */
    @Override
    public Map getFollowInfoByCustomerId(Integer id,Integer uid) {

        return customerFollowDao.getFollowInfoByCustomerId(id,uid);
    }

    /**
     * 通过followQuery条件过滤跟进信息并返回
     * @param followQuery
     * @return
     */
    @Override
    public Map listFollowQuery(FollowQuery followQuery, Map loginInfo) {
        //获取员工是否为业务员
        int roleId = (int) loginInfo.get("role_id");

        //判断角色id ==1 表示是业务员
        if (roleId == 1){
            //此时应判断是哪一个业务员 获取user的id
            int userId = (int) loginInfo.get("id");
            //查询该用户下的未回访客户
            followQuery.setUserId(userId);
        }
        int pageNo =followQuery.getPageNo();
        int pageSize =followQuery.getPageSize();
        PageHelper.startPage(pageNo, pageSize);
        PageHelper.startPage(pageNo, pageSize);
        System.out.println("测试输出followStatus:"+followQuery.getFollowStatus());
        Map map = new HashMap<>();
        map.put("rowList",customerFollowDao.listFollowQuery(followQuery));
        map.put("totalRow",customerFollowDao.getTotalQuery(followQuery));

        return map;
    }

    /**
     * 处理增加业务 将数据传递给DAO层 返回是否成功的代码
     * 该业务需加事务
     * 1将回访记录补全
     * 2生成新的下次回访信息
     * 3修改客户表的客户等级
     * @param
     * @return
     */
    @Override
    @Transactional
    public int insertFollowInfo(FollowInfo followInfo) {
        //获取followId 根据customerId 与 isDone
        int id = customerFollowDao.getFollowIdByCustomerId(followInfo.getCustomerId(),1);
        followInfo.setFollowId(id);
        followInfo.setFollowStatus(0);
        //将回访信息补全 状态为已完成0
        int updateRow = customerFollowDao.updateFollowInfo(followInfo);

        //判断updateRow的值是否为1
        if (updateRow != 1){
            throw new RuntimeException("未能保存本次客户信息的跟进");
        }


        //判断前台传递的nextFollowTime参数中是否有值 如没值 需要根据客户级别自动创建下次跟进时间
        if (followInfo.getNextDate() == null || "".equals(followInfo.getNextDate()) ){
            //获取客户级别
            String level = followInfo.getNowLevel();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            //获取本次回访时间 用到了日期转换函数
            String nowDate = followInfo.getNowDate();
            Date now = null;
            try {
                 now = sdf.parse(nowDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //使用封装的方法返回下次回访的日期
            String nextDate = ReturnNextFollowDate.returnNextDate(now,level);
            System.out.println(nextDate);
            //将下次回访日期设置到followIndo中
            followInfo.setNextDate(nextDate);
        }
        followInfo.setFollowStatus(1);
        //生成下次回访信息 回访时间 回访状态 客户id
        int rows = customerFollowDao.insertFollowInfo(followInfo);
        if (rows != 1){
            throw new RuntimeException("未能生成新的回访信息");
        }
        int followId = followInfo.getFollowId();
        System.out.println("新的followId是:"+followId);
        //修改客户表的客户等级
        Map condition = new HashMap();
        condition.put("nowLevel",followInfo.getNowLevel());
        System.out.println("当前customerId是:"+followInfo.getCustomerId());
        condition.put("customerId",followInfo.getCustomerId());
        int row = customerFollowDao.updateCustomerLevel(condition);
        return row;
    }

    /**
     * 根据客户id查询跟进历史记录
     * @param customerId
     * @return
     */
    @Override
    public List<Map> getFollowHistory(int customerId) {


        return customerFollowDao.getFollowHistory(customerId);
    }

    /**
     * 查询所有销售员信息
     * @return
     */
    @Override
    public List<Map> findUsers(int id) {
        return customerFollowDao.findUsers(id);
    }

    /**
     * 将需要分享的客户插入到user_customer中
     * @param map
     * @return
     */
    @Transactional
    @Override
    public int insertShareCustomer(Map<String, Object> map) {
        //共享给谁
        return customerFollowDao.insertShareCustomer(map);
    }

    /**
     * 当新增客户信息时,使用该方法添加一条回访信息到followinfo表
     * 需要三个信息 新增客户的id customerId ,当前日期 nextFollowTime ,回访状态 isDone
     * @return
     */
    @Override
    public int insertNewFollowInfo() {
        int customerId = 6;//模拟数据customerId
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowDate = sdf.format(date);//获取当前时间作为followInfo表的nextFollowTime
        int isDone = 1;//初始回访状态为1 意为未回访状态  0是已回访 该处使用1
        Map<String, Object> map = new HashMap<>();
        map.put("customerId",customerId);
        map.put("nextFollowTime",nowDate);
        map.put("isDone",isDone);

        return customerFollowDao.insertNewFollowInfo(map);
    }

    /**
     * 根据用户登录名shiroUserName 判断员工身份 将员工roleid返回
     * @param shiroUserName
     * @return
     */
    @Override
    public Map checkEmpRoleName(String shiroUserName) {

        return customerFollowDao.checkEmpRoleName(shiroUserName);
    }

    /**
     * 根据角色获取应查到的待回访数量
     * @param map
     * @return
     */
    @Override
    public int getCountNeedFollow(Map map) {
        //从map中取出role_id
        int roleId = (int) map.get("role_id");
        //判断角色id ==1 表示是业务员
        if (roleId == 1){
            //此时应判断是哪一个业务员 获取user的id
            int id = (int) map.get("id");
            //查询该用户下的未回访客户
            return customerFollowDao.getCountNeedFollowSelf(id);
        } else if (roleId == 3) {
            return 0;
        }

        return customerFollowDao.getCountNeedFollowAll();
    }

    /**
     * 查询所有共享的客户
     * @return
     */
    @Override
    public List<Map> listShareCustomer(Map map) {

        int roleId = (int) map.get("role_id");
        int uid = (int) map.get("id");
        if (roleId==1){
            return customerFollowDao.listShareCustomer(uid);
        }
        return customerFollowDao.listShareCustomerNone();
    }

    /**
     * 根据前台传递的客户id取消客户共享
     * @param cid
     * @return
     */
    @Override
    public int cancelShareCustomer(Integer cid) {

        return customerFollowDao.cancelShareCustomer(cid);
    }

    /**
     * 按身份查询基盘客户数据
     * @param rid
     * @return
     */
    @Override
    public List<Map> listCustomerCountByLevel(int rid, int uid) {

        return customerFollowDao.listCustomerCountByLevel(rid, uid);
    }

    /**
     * 查询客户是否已存在
     * @param cusId
     * @param shareId
     * @return
     */
    @Override
    public int customerIsExist(int cusId, int shareId) {
        return customerFollowDao.customerIsExist(cusId, shareId);
    }

}
