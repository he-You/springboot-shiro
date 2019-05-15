package com.springboot.shiro.http.response;

import com.springboot.shiro.entity.Role;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by heyou on 2019/5/14 0014.
 */
@Data
public class ResUser {
    private String username;
    private String password;
    private String roleName;
    private String permissionName;
    private String url;
    private Set<Role> roles = new HashSet<>();
}
