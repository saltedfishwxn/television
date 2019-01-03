package com.aaa.service;

import com.aaa.entity.Role;

import java.util.Map;

public interface LRoleService {
    Map<String, Object> findAllRole(Map<String, Object> query);

    Map<String, Object> delLRoleById(Integer id);

    Map<String, Object> addLRole(Role role);

    Map<String, Object> editLRoleUser(Role role);
}
