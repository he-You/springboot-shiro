package com.springboot.shiro.realm;

import com.springboot.shiro.entity.Permission;
import com.springboot.shiro.entity.Role;
import com.springboot.shiro.entity.User;
import com.springboot.shiro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by heyou on 2019/5/14 0014.
 */
@Slf4j
public class AuthRealm extends AuthorizingRealm{
    @Autowired
    private UserService userService;
    //角色权限和对应权限添加
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       /* // 从session中拿出用户对象
        User user = (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissionList = new ArrayList<>();
        Set<String> roleNameSet = new HashSet<>();

        // 获取用户的角色集
        Set<Role> roleSet = user.getRoles();
        if (!CollectionUtils.isEmpty(roleSet)) {
            for (Role role : roleSet) {
                // 添加角色名称
                roleNameSet.add(role.getRname());

                // 获取角色的权限集
                Set<Permission> permissionSet = role.getPermissions();
                if (!CollectionUtils.isEmpty(permissionSet)) {
                    for (Permission permission : permissionSet) {
                        // 添加权限名称
                        permissionList.add(permission.getName());
                    }
                }
            }
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        info.setRoles(roleNameSet);

        return info;*/
        //获取登录用户名
        String name= (String) principalCollection.getPrimaryPrincipal();
        //查询用户名称
        User user = userService.findByUserName(name);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role:user.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRname());
            for (Permission permission:role.getPermissions()) {
                //添加权限
                simpleAuthorizationInfo.addStringPermission(permission.getName());
            }
        }
        return simpleAuthorizationInfo;
    }
    // 认证登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
   /*     UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        // 获取登录的用户名
        String userName = usernamePasswordToken.getUsername();
        // 从数据库中查询用户
        User user = userService.findByUserName(userName);

        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());*/
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        User user = userService.findByUserName(name);
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, user.getPassword().toString(), getName());
            return simpleAuthenticationInfo;
        }
    }
}
