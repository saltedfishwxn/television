package com.aaa.service.impl;

import com.aaa.dao.UserMapper;
import com.aaa.entity.Resource;
import com.aaa.entity.Role;
import com.aaa.entity.Tree;
import com.aaa.entity.User;
import com.aaa.service.ResourceBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 尚冠峰
 * @Date: 2018/12/15 0015 15:46
 * @Version 1.0
 */
@Service
public class ResourceBizImpl implements ResourceBiz {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<Tree> showAllResources(String username) {
        List<Tree> trees = new ArrayList<Tree>();
        //根据名称返回用户对象
        User user = userMapper.findByName(username);
        //根据用户id返回所有的角色信息
        List<Role> roleList = userMapper.findRoleByUserId(user.getId());
        for (Role role : roleList) {
            //根据角色id获取所有的权限
            List<Resource> resourceList=null;
            if(role.getName().equals("admin")){
                resourceList = userMapper.findResourceByAdmin();
            }else{
            resourceList = userMapper.findResourceByRoleIds(role.getId());
            }
            if (resourceList == null) {
                return trees;
            }
            for (Resource resource : resourceList) {
                System.out.println("所有权限"+resource.getName());
                    Tree tree = new Tree();
                    tree.setId(resource.getId());
                    tree.setPid(resource.getPid());
                    tree.setText(resource.getName());
                    tree.setIconCls(resource.getIcon());
                    tree.setAttributes(resource.getUrl());
                    tree.setOpenMode(resource.getOpenMode());
                    tree.setState(resource.getOpened()+"");
                    trees.add(tree);
            }
        }
        return trees;
    }
}
