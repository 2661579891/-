package com.hopu.phone_admin.config;

import com.hopu.phone_admin.realm.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration  //定义配置类的注解
//配置shiro配置类
public class ShiroConfig {

    //返回密码匹配器
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //散列算法 使用MD5
        credentialsMatcher.setHashAlgorithmName("md5");
        //设置加密次数
        credentialsMatcher.setHashIterations(2);
        //设置密文为16进制  false是以base64编码
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    //配置自定义realm
    @Bean
    public UserRealm userRealm() {
        //创建一个自己定义的realm
        UserRealm userRealm = new UserRealm();
        //设置密码匹配器
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        //设置缓存为不缓存用户信息
        userRealm.setCachingEnabled(false);
        return userRealm;
    }

    //返回安全管理器 设置自定义Realm
    @Bean
    public SecurityManager securityManager() {
        //创建web的SecurityManager
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //配置安全管理器的realm
        defaultWebSecurityManager.setRealm(userRealm());
        return defaultWebSecurityManager;
    }

    //配置过滤器实现用户URL登录验证
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        //创建
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //配置登录URL  登录失败就自动跳转
        shiroFilterFactoryBean.setLoginUrl("login.html");
        //配置登录失败的url
        shiroFilterFactoryBean.setUnauthorizedUrl("failed.html");
        //配置拦截规则，不拦截的在前，拦截的在后
        Map<String, String> map = new LinkedHashMap<>();
        //添加不拦截的  anon是不拦截标志
        map.put("/login.html", "anon");     //登录
        map.put("/user/login", "anon");
        map.put("/failed.html", "anon");    //失败界面
        map.put("/register.html", "anon");  //注册
        map.put("/user/register", "anon");
        map.put("/static/**", "anon");      //js/css等静态资源
        //添加拦截的   拦截其余所有资源
//        map.put("/**","authc");
        //配置拦截规则
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    //下面的配置用于启动requireRose 和 requirePermission注解 基于SpringAop实现
    //配置后置处理器
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }
}
