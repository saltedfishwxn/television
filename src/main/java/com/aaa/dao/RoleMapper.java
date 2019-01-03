package com.aaa.dao;

import com.aaa.entity.Role;

public interface RoleMapper {
    int insert(Role record);

    int insertSelective(Role record);
}