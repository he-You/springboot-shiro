package com.springboot.shiro.config;

import com.springboot.shiro.matcher.CredentialMatcher;
import com.springboot.shiro.realm.AuthRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by heyou on 2019/5/14 0014.
 * @description: shiro配置类
 */
@Configuration
public class ShiroConfiguration {
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        // 登录的url
        bean.setLoginUrl("/login");
        // 登录成功后跳转的url
        bean.setSuccessUrl("/index");
        // 权限拒绝时跳转的url
        bean.setUnauthorizedUrl("/unauthorize");

        // 定义请求拦截规则，key是正则表达式用于匹配访问的路径，value则用于指定使用什么拦截器进行拦截
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 拦截index接口，authc表示需要认证才能访问
        filterChainDefinitionMap.put("/index", "authc");
        // anon表示不拦截
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/loginUser", "anon");
        // 指定admin接口只允许admin角色的用户访问
        filterChainDefinitionMap.put("/admin", "roles[admin]");
        // 用户在登录后可以访问所有的接口
        filterChainDefinitionMap.put("/**", "user");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return bean;
    }
    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
        // 设置自定义的SecurityManager
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(authRealm);

        return manager;
    }

    @Bean("authRealm")
    public AuthRealm authRealm(@Qualifier("credentialMatcher") CredentialMatcher matcher) {
        // 设置自定义的Realm
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCredentialsMatcher(matcher);

        return authRealm;
    }

    @Bean("credentialMatcher")
    public CredentialMatcher credentialMatcher() {
        // 设置自定义密码校验规则
        return new CredentialMatcher();
    }

    // =========== spring 与 shiro 关联的相关配置 ============

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
        // 设置spring在对shiro进行处理的时候，使用的SecurityManager为我们自定义的SecurityManager
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);

        return advisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        // 设置代理类
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);

        return creator;
    }
}
