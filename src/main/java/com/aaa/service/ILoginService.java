package com.aaa.service;

import com.aaa.entity.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: 尚冠峰
 * @Date: 2018/12/12 0012 21:40
 * @Version 1.0
 */
public interface ILoginService {

    User findByName(String name);
    User addUser(User user);
    //添加角色
     Role addRole(Role role) ;

    String getLogin(String shiroUserName);

   int updatePw(UpdatePw pw);

    ResultsModel forgetPw(Map map);

    int  update(Map map);

    List<Resource> getPower();

    List<Map> getPowerByUsername(int id);

    List<Map> power();

    int submitPower(List list, int roleId);
}
