package com.aaa.service;

import com.aaa.entity.Users;

import java.util.List;
import java.util.Map;

public interface UsersService {

    Map<String, Object> listRoles();

    Map<String, Object> listUser(Map<String, Object> query);

    Map<String, Object> phoneChange(Map<String, Object> query);

    Map<String, Object> addUser(Users users);

    Map<String, Object> editUser(Users users);

    Map<String, Object> toExcelUsers(Map<String, List<Users>> query);

    Map<String, Object> inExcelUsers(String path);

    /*Map<String, Object> upload(String url);*/
}
