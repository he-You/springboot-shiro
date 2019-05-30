package com.springboot.shiro;

import com.springboot.shiro.entity.Permission;
import com.springboot.shiro.entity.Role;
import com.springboot.shiro.entity.User;
import com.springboot.shiro.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

/**
 * Created by heyou on 2019/5/15 0015.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Test
    public void findByUsername() {
        User u = userService.findByUserName("admin");
        Set<Role> roleSet = u.getRoles();
        for (Role role : roleSet){
            Set<Permission> permissionSet = role.getPermissions();
            for (Permission permission : permissionSet){
                System.out.println(permission.getName());
            }
            System.out.println(role.getRname());
        }
    }
}
