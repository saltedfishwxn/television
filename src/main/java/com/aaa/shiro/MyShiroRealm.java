package com.aaa.shiro;

import com.aaa.entity.Resource;
import com.aaa.entity.Role;
import com.aaa.entity.User;
import com.aaa.service.ILoginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: 尚冠峰
 * @Date: 2018/12/12 0012 21:39
 * @Version 1.0
 */
//实现AuthorizingRealm接口用户用户认证
public class MyShiroRealm extends AuthorizingRealm {
    //用于用户查询
    @Autowired
    private ILoginService loginService;

    //角色权限和对应权限添加
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String name= (String) principalCollection.getPrimaryPrincipal();
        //查询用户名称
        User user = loginService.findByName(name);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role:user.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getName());
            for (Resource resource:role.getResources()) {
                //添加权限，并排除为空的权限
                if(resource.getUrl()!=null && resource.getUrl().length()>0){
                    System.out.println(resource.getUrl());
                    simpleAuthorizationInfo.addStringPermission(resource.getUrl());
                }
            }
        }
        return simpleAuthorizationInfo;
    }

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {//加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        User user = loginService.findByName(name);
        if (user == null) {
            //这里返回后会报出对应异常
            //throw new RuntimeException("账号不存在！");
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            //第一个参数是前端传入的用户名
            //第二个参数是数据库中的密文
            //第三个参数是数据库中的盐值
            //返回的是当前会话中保存的名字，前端传入的
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, user.getPassword(),ByteSource.Util.bytes(user.getSalt()), getName());
            return simpleAuthenticationInfo;
        }

    }

}