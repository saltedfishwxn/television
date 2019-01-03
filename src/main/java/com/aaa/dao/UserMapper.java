package com.aaa.dao;

import com.aaa.entity.Resource;
import com.aaa.entity.Role;
import com.aaa.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface UserMapper {
  /*  int insert(User record);

    int insertSelective(User record);*/
    User findByName(String username);
    List<Role> findRoleByUserId(Long userid);
    List<Resource> findResourceByRoleIds(Long roleId);

    String getLogin(String shiroUserName);

    void updatePw(User user1);

    int forgetPw(Map map);

    int update(User user);

  List<Resource> getPower();

  List<Map> getPowerByUsername(int id);

    List<Map> power();

  List<Resource> findResourceByAdmin();

    void  submitPower(int id, int roleId);

  void deleteRole(int roleId);
}