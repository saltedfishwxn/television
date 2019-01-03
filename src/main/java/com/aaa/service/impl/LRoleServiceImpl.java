package com.aaa.service.impl;

import com.aaa.dao.LRoleMapper;
import com.aaa.entity.Role;
import com.aaa.service.LRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LRoleServiceImpl implements LRoleService {

    @Autowired
    private LRoleMapper dao;

    @Override
    public Map<String, Object> findAllRole(Map<String, Object> query) {
        HashMap<String, Object> map = new HashMap<>();
        List<Role> listLRole = dao.findAllRole(query);
        map.put("listLRole",listLRole);
        return map;
    }

    @Override
    public Map<String, Object> delLRoleById(Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        int flag = 0;
        dao.delLRoleById(id);
        flag = 1;
        if(flag>0){
            map.put("delLRoleByIdInfo","删除成功");
        }else{
            map.put("delLRoleByIdInfo","删除失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> addLRole(Role role) {
        HashMap<String, Object> map = new HashMap<>();
        int flag = 0;
        dao.addLRole(role);
        flag = 1;
        if(flag>0){
            map.put("addLRoleInfo","添加成功");
        }else{
            map.put("addLRoleInfo","添加失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> editLRoleUser(Role role) {
        HashMap<String, Object> map = new HashMap<>();
        int flag = 0;
        dao.editLRoleUser(role);
        flag = 1;
        if(flag>0){
            map.put("editLRoleUserInfo","修改成功");
        }else{
            map.put("editLRoleUserInfo","修改失败");
        }
        return map;
    }
}
