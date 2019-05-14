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

import java.util.*;

/**
 * Created by heyou on 2019/5/14 0014.
 */
@Slf4j
public class AuthRealm extends AuthorizingRealm{
    @Autowired
    private UserService userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //从session获取用户对象，与Http的session不同
        User user = (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissionList = new ArrayList<>();
        Set<String> roleNameSet = new HashSet<>();
        //获取用户的角色集
        Set<Role> roleSet = user.getRoles();
        if(!CollectionUtils.isEmpty(roleSet)){
            for (Role role:roleSet) {
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
        return info;
    }
    // 认证登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        // 获取登录的用户名
        String userName = usernamePasswordToken.getUsername();
        // 从数据库中查询用户
        User user = userService.findByUserName(userName);
        log.info("用户{}",user);
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());

    }
}
