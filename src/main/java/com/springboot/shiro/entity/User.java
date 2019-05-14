package com.springboot.shiro.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator 2019/05/14
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Integer uid;

    private String username;

    private String password;

    private Set<Role> roles = new HashSet<>();

    private static final long serialVersionUID = 1L;
}