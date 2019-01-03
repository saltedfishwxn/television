package com.aaa.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 陈建
 * @Date: 2018/12/12 0012 21:41
 * @Version 1.0
 */
@Configuration
public class ShiroConfiguration {
    //设置加密方式和加密次数
    @Bean
    public HashedCredentialsMatcher HashedCredentialsMatcherBean(){
        HashedCredentialsMatcher credentialsMatcher =new HashedCredentialsMatcher();
        credentialsMatcher.setHashIterations(1000);
        credentialsMatcher.setHashAlgorithmName("MD5");
        return credentialsMatcher;
    }
    //将自己的验证方式加入容器
    @Bean
    public MyShiroRealm myShiroRealm(HashedCredentialsMatcher credentialsMatcher) {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(credentialsMatcher);
        return myShiroRealm;
    }

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager(MyShiroRealm myShiroRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm);
        return securityManager;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> map = new HashMap<String, String>();
        map.put("/fg/**","anon");
        map.put("/static/**","anon");
        map.put("/js/**","anon");
        map.put("/img/**","anon");
        map.put("/loginPage/**","anon");
        map.put("/time/**","anon");
        map.put("/crm/js/**","anon");
        map.put("/crm/js/image/*.png","anon");

        map.put("/**","authc");
        //map.put("/**","anon");


        //登录
        shiroFilterFactoryBean.setLoginUrl("/log/login");

        //首页
        shiroFilterFactoryBean.setSuccessUrl("/log/index");
        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/log/error");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    //加入注解的使用，不加入这个注解不生效
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }



}