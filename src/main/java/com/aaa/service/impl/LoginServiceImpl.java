package com.aaa.service.impl;

import com.aaa.dao.UserMapper;
import com.aaa.entity.*;
import com.aaa.service.ILoginService;
import com.aaa.shiro.ShiroUtil;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: 尚冠峰
 * @Date: 2018/12/12 0012 22:17
 * @Version 1.0
 */
@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired

    private UserMapper userMapper;
    @Override
    public User findByName(String name) {
        User user = userMapper.findByName(name);
        if(user==null){
            return null;
        }else{
        List<Role> roleList = userMapper.findRoleByUserId(user.getId());

            List<Role> roleListNew=new ArrayList<Role>();
            for (Role role : roleList) {
                if(role.getName().equals("admin")){
                    List<Resource> resourceList = userMapper.findResourceByAdmin();
                    role.setResources(resourceList);
                    roleListNew.add(role);
                }else{
                   /* List<Resource> resourceList = userMapper.findResourceByRoleIds(role.getId());
                    role.setResources(resourceList);
                    roleListNew.add(role);*/
                }

            }
            user.setRoles(roleListNew);
            return user;
        }

    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public Role addRole(Role role) {
        return null;
    }

    @Override
    public String getLogin(String shiroUserName) {
        return userMapper.getLogin(shiroUserName);
    }

    @Override
    public int updatePw(UpdatePw pw) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(pw.getLoginname(), pw.getPassword());
        try{
            User user = new User();
            user.setPassword(pw.getPass());
            User user1 = ShiroUtil.encryptPassword(user);
            user1.setLoginName(pw.getLoginname());
            userMapper.updatePw(user1);
            return 1;
        }catch(IncorrectCredentialsException e){
            return 0;
        }
    }

    @Override
    public ResultsModel forgetPw(Map map) {
        ResultsModel resultsModel = new ResultsModel();
        int i = userMapper.forgetPw(map);
       if(i==1){
           map.put("state",1);
           //String code = GetCodeUtil.getCode(map.get("name").toString());
           resultsModel.setRetStatus(i);
           resultsModel.setMessage("555");
       }else{
           resultsModel.setRetStatus(0);
           resultsModel.setMessage("没有此手机号");
       }

        return resultsModel;
    }

    @Override
    public int update(Map map) {
        User user = new User();
        user.setPassword(map.get("password").toString());
        user.setPhone(map.get("phone").toString());
        ShiroUtil.encryptPassword(user);
        int i=userMapper.update(user);
        return i;
    }

    @Override
    public List<Resource> getPower() {
        List<Resource> power = userMapper.getPower();
        return power;
    }

    @Override
    public List<Map> getPowerByUsername(int id) {
        return  userMapper.getPowerByUsername(id);

    }

    @Override
    public List<Map> power() {
        return  userMapper.power();
    }


    @Override
    @Transactional
    public int submitPower(List list, int roleId) {
        userMapper.deleteRole(roleId);
        for (int i = 0; i <list.size() ; i++) {
            int id = (int) list.get(i);
            userMapper.submitPower(id,roleId);
        }
        return 0;
    }
}
