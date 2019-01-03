package com.aaa.dao;

import com.aaa.entity.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UsersMapper {
    List<Map<String, Object>> listRoles();

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> listUser(Map<String, Object> query);

    Integer getTotalUser(Map<String, Object> query);

    List<Map<String, Object>> phoneChange(Map<String, Object> query);

    void addUser(Users user);

    void addUserToRole(Users users);

    void editUser(Users users);

    List<Map<String, Object>> getUserId(Users users);

    void updateUserRole(Users users);
}