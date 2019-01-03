package com.aaa.dao;

import com.aaa.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LRoleMapper {
    List<Role> findAllRole(Map<String, Object> query);

    void delLRoleById(Integer id);

    void addLRole(Role role);

    void editLRoleUser(Role role);
}
